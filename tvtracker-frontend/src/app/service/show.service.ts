import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Show } from '../models/show';
import { UserShowDto } from '../models/userShowDto';

@Injectable({
  providedIn: 'root'
})
export class ShowService {

  private baseUrl = 'http://localhost:8080/api/shows';

  constructor(private http: HttpClient) {}

  getAllShows(): Observable<Show[]> {
    return this.http.get<Show[]>(`${this.baseUrl}/allShows`);
  }

  getAllShowsByUserId(userId: number): Observable<UserShowDto[]> {
    return this.http.get<UserShowDto[]>(`${this.baseUrl}/allShowsByUser/${userId}`);
  }
}
