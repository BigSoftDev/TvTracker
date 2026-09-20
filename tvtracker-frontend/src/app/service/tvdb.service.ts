import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Show } from '../models/show';

@Injectable({
  providedIn: 'root'
})
export class TvdbService {

  private baseUrl = 'http://localhost:8080/api/util';

  constructor(private http: HttpClient) {}

  getTopShows(): Observable<Show[]> {
    return this.http.get<Show[]>(`${this.baseUrl}/top-shows`);
  }
}
