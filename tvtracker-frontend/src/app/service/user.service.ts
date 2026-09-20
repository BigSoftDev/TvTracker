import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface User {
  id: number;
  username: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/auth';
  private storageKey = 'tvtracker_user';

  currentUser = signal<User | null>(this.loadUser());

  constructor(private http: HttpClient) {}

  getUsers() {
    return this.http.get<User[]>(`${this.apiUrl}/users`);
  }

  login(username: string) {
    return this.http.post<User>(`${this.apiUrl}/login`, {
      username
    });
  }

  setCurrentUser(user: User) {
    this.currentUser.set(user);
    localStorage.setItem(this.storageKey, JSON.stringify(user));
  }

  logout() {
    this.currentUser.set(null);
    localStorage.removeItem(this.storageKey);
  }

  isLoggedIn() {
    return this.currentUser() !== null;
  }

  createUser(username: string) {
    return this.http.post<User>(
      `${this.apiUrl}/new-user/${username}`,
      { username }
    );
  }

  deleteUser(userId: number) {
    return this.http.delete(`${this.apiUrl}/delete-user/${userId}`);
  }

  private loadUser(): User | null {
    const savedUser = localStorage.getItem(this.storageKey);

    if (!savedUser) {
      return null;
    }

    return JSON.parse(savedUser);
  }
}