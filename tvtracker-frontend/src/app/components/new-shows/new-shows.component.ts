import { Component, inject, signal } from '@angular/core';
import { ShowService } from '../../service/show.service';
import { ShowIndexComponent } from '../show-index/show-index.component';
import { UserService } from '../../service/user.service';
import { UserShowService } from '../../service/userShow.service';
import { toObservable } from '@angular/core/rxjs-interop';
import { switchMap, of } from 'rxjs';
import { ShowAction } from '../../models/showAction';
import { UserShowDto } from '../../models/userShowDto';

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
  private userShowService = inject(UserShowService);

  user = this.userService.currentUser;

  userShows = signal<UserShowDto[]>([]);

  showActions: ShowAction[] = [
    {
      label: 'Remove from MyShows',
      visible: show => this.isShowFollowed(show),
      execute: show => this.removeFromMyShows(show)
    },
    {
      label: 'Add to MyShows',
      visible: show => !this.isShowFollowed(show),
      execute: show => this.addToMyShows(show)
    }
  ];

  constructor() {
    toObservable(this.user)
      .pipe(
        switchMap(user =>
          user
            ? this.showService.getAllShowsByUserId(user.id)
            : of([])
        )
      )
      .subscribe(shows => {
        this.userShows.set(shows);
      });
  }

  isShowFollowed(show: UserShowDto) {
    return !!show.userShowId;
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
          shows.map(userShow =>
            userShow.show.id === currentShow.id
              ? {
                  ...userShow,
                  userShowId: 0
                }
              : userShow
          )
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

  addToMyShows(show: UserShowDto) {
    const currentShow = show.show;
    const user = this.userService.currentUser();

    if (!user) {
      console.error('No user is currently selected.');
      return;
    }

    this.userShowService.followShow(user.id, currentShow.id).subscribe({
      next: userShow => {
this.userShows.update(shows =>
  shows.map(userShowDto =>
    userShowDto.show.id === currentShow.id
      ? {
          ...userShowDto,
          userShowId: userShow.id,
          favorite: userShow.favorite,
          rating: userShow.rating,
          status: userShow.status
        }
      : userShowDto
  )
);

        console.log(
          `Successfully added show: ${currentShow.name} to My Shows for user: ${user.username}`
        );
      },
      error: err => {
        console.error('Error adding show to My Shows:', err);
      }
    });
  }
}