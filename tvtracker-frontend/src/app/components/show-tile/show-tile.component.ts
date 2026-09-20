import { Component, computed, inject, Input, input, OnInit } from '@angular/core';
import { Show } from '../../models/show';
import { MatButtonModule } from '@angular/material/button';
import { UserService } from '../../service/user.service';
import { UserShowService } from '../../service/userShow.service';

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

  show = input<Show | null>(null);

  fullLink = computed(() => {

    const currentShow = this.show();

    if (!currentShow?.imgLink) return '';

    const base = 'https://artworks.thetvdb.com';

    return `${base}${currentShow.imgLink.startsWith('/') ? '' : '/'}${currentShow.imgLink}`;
  });



  addToMyShows() {
    const currentShow = this.show();
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
