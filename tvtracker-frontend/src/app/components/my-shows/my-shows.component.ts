import { Component, inject, signal } from '@angular/core';
import { toObservable } from '@angular/core/rxjs-interop';
import { of } from 'rxjs';
import { switchMap } from 'rxjs/operators';

import { ShowIndexComponent } from '../show-index/show-index.component';
import { UserService } from '../../service/user.service';
import { UserShowService } from '../../service/userShow.service';
import { ShowAction } from '../../models/showAction';
import { UserShowDto } from '../../models/userShowDto';
import { Router } from '@angular/router';

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
  private router = inject(Router);

  user = this.userService.currentUser;

  userShows = signal<UserShowDto[]>([]);

  showActions: ShowAction[] = [
    {
      label: 'Remove from MyShows',
      visible: show => !!show.userShowId,
      execute: show => this.removeFromMyShows(show)
    },
    {
      label: 'View Details',
      visible: () => true,
      execute: (show) => this.goToShowDetails(show.show.id)
    }
  ];

  constructor() {
    toObservable(this.user)
      .pipe(
        switchMap(user =>
          user
            ? this.userShowService.getUserShows(user.id)
            : of([])
        )
      )
      .subscribe(shows => {
        this.userShows.set(shows);
      });
  }

  goToShowDetails(showId: number) {
  this.router.navigate(['/show-details'], {
    queryParams: { showId }
  });
}

  removeFromMyShows(show: UserShowDto) {
    const currentShow = show.show;
    const user = this.userService.currentUser();

    if (!user) {
      console.error('No user is currently selected.');
      return;
    }

    this.userShowService.unfollowShow(user.id, currentShow.id).subscribe({
      next: () => {
        this.userShows.update(shows =>
          shows.filter(userShow => userShow.show.id !== currentShow.id)
        );

        console.log(
          `Successfully removed show: ${currentShow.name} from My Shows for user: ${user.username}`
        );
      },
      error: err => {
        console.error('Error removing show from My Shows:', err);
      }
    });
  }
}