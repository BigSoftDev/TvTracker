import { Component, Input, OnInit } from '@angular/core';
import { Show } from '../../models/show';
import { ShowAction } from '../../models/showAction';
import { EpisodeTileComponent } from '../episode-tile/episode-tile.component';
import { UserEpisodeDto } from '../../models/userEpisodeDto';
import { EpisodeAction } from '../../models/episodeAction';

@Component({
  selector: 'episode-index',
  standalone: true,
  templateUrl: './episode-index.component.html',
  styleUrls: ['./episode-index.component.css'],
  imports: [EpisodeTileComponent]
})
export class EpisodeIndexComponent{

  @Input() dtos: UserEpisodeDto[] = [];
  @Input() actions: EpisodeAction [] = [];

  constructor(
  ) { }


}
