import { Component, computed, inject } from '@angular/core';
import { Show } from '../../models/show';
import { ShowService } from '../../service/show.service';
import { ShowIndexComponent } from '../show-index/show-index.component';
import { UserService } from '../../service/user.service';
import { UserShowService } from '../../service/userShow.service';
import { toSignal, toObservable } from '@angular/core/rxjs-interop';
import { switchMap, of } from 'rxjs';

@Component({
  selector: 'new-shows',
  standalone: true,
  imports: [ShowIndexComponent],
  templateUrl: './new-shows.component.html',
  styleUrl: './new-shows.component.scss'
})
export class NewShowsComponent {

    private userService = inject(UserService);
    private showService = inject(ShowService);
  
    user = this.userService.currentUser;
      userShows = toSignal(
        toObservable(this.user).pipe(
          switchMap(user =>
            user
              ? this.showService.getAllShowsByUserId(user.id)
              : of([])
          )
        ),
        { initialValue: [] }
      );
}
