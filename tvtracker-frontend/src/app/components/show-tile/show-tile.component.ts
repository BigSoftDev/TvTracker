import { Component, computed, inject, Input, input, OnInit } from '@angular/core';
import { Show } from '../../models/show';
import { MatButtonModule } from '@angular/material/button';
import { UserService } from '../../service/user.service';
import { UserShowService } from '../../service/userShow.service';
import { UserShowDto } from '../../models/userShowDto';

@Component({
  selector: 'show-tile',
  standalone: true,
  templateUrl: './show-tile.component.html',
  styleUrls: ['./show-tile.component.css'],
  imports: [MatButtonModule]
})
export class ShowTileComponent {

  private userService = inject(UserService);
  private userShowService = inject(UserShowService);

  userShow = input<UserShowDto | null>(null);

  fullLink = computed(() => {

    const currentShow = this.userShow()?.show;

    if (!currentShow?.imgLink) return '';

    const base = 'https://artworks.thetvdb.com';

    return `${base}${currentShow.imgLink.startsWith('/') ? '' : '/'}${currentShow.imgLink}`;
  });

  removeFromMyShows() {
    const currentShow = this.userShow()?.show;
    if (!currentShow) return;
    const user = this.userService.currentUser();
    if (!user) {
      console.error('No user is currently selected.');
      return;
    }

    this.userShowService.unfollowShow(user.id, currentShow.id).subscribe({
      next: () => {
        console.log(`Successfully removed show: ${currentShow.name} from My Shows for user: ${user.username}`);
      },
      error: (err) => {
        console.error('Error removing show from My Shows:', err);
      }
    });

    // Logic to remove the show from the user's list of shows
    console.log(`Removing show: ${currentShow.name} from My Shows`);
  }


  addToMyShows() {
    const currentShow = this.userShow()?.show;
    if (!currentShow) return;
    const user = this.userService.currentUser();
    if (!user) {
      console.error('No user is currently selected.');
      return;
    }

    this.userShowService.followShow(user.id, currentShow.id).subscribe({
      next: () => {
        console.log(`Successfully added show: ${currentShow.name} to My Shows for user: ${user.username}`);
      },
      error: (err) => {
        console.error('Error adding show to My Shows:', err);
      }
    });


    // Logic to add the show to the user's list of shows
    console.log(`Adding show: ${currentShow.name} to My Shows`);
  }

}
