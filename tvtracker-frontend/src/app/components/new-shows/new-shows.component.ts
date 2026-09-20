import { Component } from '@angular/core';
import { Show } from '../../models/show';
import { ShowService } from '../../service/show.service';
import { ShowIndexComponent } from '../show-index/show-index.component';

@Component({
  selector: 'new-shows',
  standalone: true,
  imports: [ShowIndexComponent],
  templateUrl: './new-shows.component.html',
  styleUrl: './new-shows.component.scss'
})
export class NewShowsComponent {
    shows: Show[] = [];
  
    constructor(
      private showService : ShowService,
    ) { }
  
    ngOnInit() {
      this.showService.getAllShows().subscribe(result =>{
        this.shows = result;
        this.shows.sort((a, b) => b.score - a.score);
      })
    }

}
