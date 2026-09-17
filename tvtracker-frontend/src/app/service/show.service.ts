import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Series } from '../models/series';

@Injectable({
  providedIn: 'root'
})
export class ShowService {

  private baseUrl = 'http://localhost:8080/api/shows';

  constructor(private http: HttpClient) {}

  getAllShows(): Observable<Series[]> {
    return this.http.get<Series[]>(`${this.baseUrl}/allShows`);
  }
}
