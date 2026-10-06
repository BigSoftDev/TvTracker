import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserEpisodeDto } from '../models/userEpisodeDto';

@Injectable({
  providedIn: 'root'
})
export class EpisodeService {

  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api/episodes';

  getEpisodesByShow(
    showId: number,
    userId: number
  ): Observable<UserEpisodeDto[]> {
    return this.http.get<UserEpisodeDto[]>(
      `${this.baseUrl}/show/${showId}`,
      {
        params: {
          userId
        }
      }
    );
  }

  markWatched(
    episodeId: number,
    userId: number
  ): Observable<void> {
    return this.http.post<void>(
      `${this.baseUrl}/${episodeId}/watched`,
      null,
      {
        params: {
          userId
        }
      }
    );
  }

  markUnwatched(
    episodeId: number,
    userId: number
  ): Observable<void> {
    return this.http.delete<void>(
      `${this.baseUrl}/${episodeId}/watched`,
      {
        params: {
          userId
        }
      }
    );
  }
}