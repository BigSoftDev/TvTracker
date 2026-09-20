import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserShow } from '../models/userShow';
import { UserShowDto } from '../models/userShowDto';

export interface User {
  id: number;
  username: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserShowService {

  private apiUrl = 'http://localhost:8080/api/user-shows';
    constructor(private http: HttpClient) {}
    public followShow(userId: number, showId: number) {
        return this.http.post(`${this.apiUrl}/follow`, null, {
            params: { userId: userId.toString(), showId: showId.toString() }
        });
    }

    public getFollowing(userId: number) {
        return this.http.get<UserShow[]>(`${this.apiUrl}/following/${userId}`);
    }

    public getUserShows(userId: number) {
    return this.http.get<UserShowDto[]>(`${this.apiUrl}/get-user-shows/${userId}`);
    }


}
