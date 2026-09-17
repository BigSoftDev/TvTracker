import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Series } from '../models/series';

@Injectable({
  providedIn: 'root'
})
export class TvdbService {

  private baseUrl = 'http://localhost:8080/api/util';

  constructor(private http: HttpClient) {}

  getTopShows(): Observable<Series[]> {
    return this.http.get<Series[]>(`${this.baseUrl}/top-shows`);
  }
}
