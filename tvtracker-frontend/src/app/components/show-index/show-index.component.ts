import { Component, Input, OnInit } from '@angular/core';
import { Show } from '../../models/show';
import { ShowTileComponent } from "../show-tile/show-tile.component";
import { TvdbService } from '../../service/tvdb.service';
import { ShowService } from '../../service/show.service';
import { UserShowDto } from '../../models/userShowDto';

@Component({
  selector: 'show-index',
  standalone: true,
  templateUrl: './show-index.component.html',
  styleUrls: ['./show-index.component.css'],
  imports: [ShowTileComponent]
})
export class ShowIndexComponent{

  @Input() dtos: UserShowDto[] = [];

  constructor(
  ) { }


}
