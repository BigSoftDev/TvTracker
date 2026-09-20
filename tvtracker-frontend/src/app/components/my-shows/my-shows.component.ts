import { Component, computed, inject } from '@angular/core';
import { toObservable, toSignal } from '@angular/core/rxjs-interop';
import { of } from 'rxjs';
import { switchMap } from 'rxjs/operators';

import { ShowIndexComponent } from '../show-index/show-index.component';
import { UserService } from '../../service/user.service';
import { UserShowService } from '../../service/userShow.service';
import { Show } from '../../models/show';

@Component({
  selector: 'my-shows',
  standalone: true,
  imports: [ShowIndexComponent],
  templateUrl: './my-shows.component.html',
  styleUrl: './my-shows.component.scss'
})
export class MyShowsComponent {

  private userService = inject(UserService);
  private userShowService = inject(UserShowService);

  user = this.userService.currentUser;

  userShows = toSignal(
    toObservable(this.user).pipe(
      switchMap(user =>
        user
          ? this.userShowService.getUserShows(user.id)
          : of([])
      )
    ),
    { initialValue: [] }
  );

  shows = computed<Show[]>(() =>
    this.userShows().map(userShow => userShow.show)
  );
}