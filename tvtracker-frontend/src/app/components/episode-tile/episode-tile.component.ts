import { Component, computed, inject, Input, input, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { UserEpisodeDto } from '../../models/userEpisodeDto';
import { EpisodeAction } from '../../models/episodeAction';

@Component({
  selector: 'episode-tile',
  standalone: true,
  templateUrl: './episode-tile.component.html',
  styleUrls: ['./episode-tile.component.css'],
  imports: [MatButtonModule]
})
export class EpisodeTileComponent {

  userEpisode = input<UserEpisodeDto | null>(null);
  actions = input<EpisodeAction[]>([]);

}
