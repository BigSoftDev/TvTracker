import { Component, OnInit } from '@angular/core';
import { Series } from '../../models/series';
import { ShowTileComponent } from "../show-tile/show-tile.component";
import { TvdbService } from '../../service/tvdb.service';
import { ShowService } from '../../service/show.service';

@Component({
  selector: 'app-show-index',
  standalone: true,
  templateUrl: './show-index.component.html',
  styleUrls: ['./show-index.component.css'],
  imports: [ShowTileComponent]
})
export class ShowIndexComponent implements OnInit {

  shows: Series[] = [];

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
