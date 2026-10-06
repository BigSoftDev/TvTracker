import { Component } from '@angular/core';
  import { ActivatedRoute } from '@angular/router';
import { inject } from '@angular/core';
import { EpisodeService } from '../../service/episode.service';
import { UserService } from '../../service/user.service';
import { UserEpisodeDto } from '../../models/userEpisodeDto';
import { Show } from '../../models/show';
import { ShowTileComponent } from '../show-tile/show-tile.component';
import { UserShowDto } from '../../models/userShowDto';
import { EpisodeIndexComponent } from '../episode-index/episode-index.component';
import { KeyValuePipe } from '@angular/common';
import { EpisodeAction } from '../../models/episodeAction';


@Component({
  selector: 'show-details',
  standalone: true,
  imports: [ShowTileComponent, EpisodeIndexComponent, KeyValuePipe],
  templateUrl: './show-details.component.html',
  styleUrl: './show-details.component.scss'
})
export class ShowDetailsComponent {


private route = inject(ActivatedRoute);
  private episodeService = inject(EpisodeService);
  private userService = inject(UserService);

  episodes: Map<number, UserEpisodeDto[]> = new Map();
  actions: EpisodeAction[] = [
    {
      label: 'Mark as Watched',
      visible: (episode) => !episode.isWatched,
      execute: (episode) => {
        this.markEpisodeAsWatched(episode, true);
        }},
            {
      label: 'Mark as Unwatched',
      visible: (episode) => episode.isWatched,
      execute: (episode) => {
        this.markEpisodeAsWatched(episode, false);
        }
    }
  ];
  show: UserShowDto | null = null;
  fullLink : string | null = null;
  

  ngOnInit() {
    const showId = Number(this.route.snapshot.queryParamMap.get('showId'));
    const user = this.userService.currentUser();

    if (!showId || !user) {
      return;
    }

    this.episodeService.getEpisodesByShow(showId, user.id).subscribe({
      next: episodes => {

  this.episodes = episodes.reduce((map, userEpisode) => {
  const season = userEpisode.episode.seasonNumber;

  if (!map.has(season)) {
    map.set(season, []);
  }

  map.get(season)!.push(userEpisode);

  return map;
}, new Map<number, UserEpisodeDto[]>());
        const show = episodes[0].episode.show;
        const userShowDto: UserShowDto = {
          show: show || null
        };
        this.show = userShowDto;
        this.fullLink = show?.imgLink ? `https://artworks.thetvdb.com${show.imgLink.startsWith('/') ? '' : '/'}${show.imgLink}` : null;
      },
      error: error => {
        console.error('Error loading episodes:', error);
      }
    });
  }
   markEpisodeAsWatched(episode: UserEpisodeDto, isWatched: boolean):void {
    if(isWatched){
      this.episodeService.markUnwatched(episode.episode.id, this.userService.currentUser()!.id).subscribe(() => {
        episode.isWatched = isWatched;
      });
    } else {
      this.episodeService.markWatched(episode.episode.id, this.userService.currentUser()!.id).subscribe(() => {
        episode.isWatched = isWatched;
      });
    }
  }
}


