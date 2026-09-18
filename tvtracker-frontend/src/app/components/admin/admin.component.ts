import { Component } from '@angular/core';
import { TvdbService } from '../../service/tvdb.service';

@Component({
  selector: 'admin',
  standalone: true,
  imports: [],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.scss'
})
export class AdminComponent {
  constructor(private tvdbService: TvdbService) {}

  fetchShowData() {
    this.tvdbService.getTopShows().subscribe(
      (shows) => {
        console.log('Fetched shows:', shows.length);
        // You can add logic here to handle the fetched shows, e.g., display them in the UI
      },
      (error) => {
        console.error('Error fetching shows:', error);
      }
    );
  }
}
