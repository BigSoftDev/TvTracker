import { Component, signal, Signal } from '@angular/core';
import { TvdbService } from '../../service/tvdb.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { User, UserService } from '../../service/user.service';

@Component({
  selector: 'admin',
  standalone: true,
  imports: [],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.scss'
})
export class AdminComponent {

users = signal<User[]>([]);

constructor(
  private tvdbService: TvdbService,
  private userService: UserService
) {
  this.loadUsers();
}

loadUsers() {
  this.userService.getUsers().subscribe(users => {
    this.users.set(users);
  });
}

fetchShowData() {
  this.tvdbService.getTopShows().subscribe(
    (shows) => {
      console.log('Fetched shows:', shows.length);
    },
    (error) => {
      console.error('Error fetching shows:', error);
    }
  );
}

deleteUser(user: User) {
  this.userService.deleteUser(user.id).subscribe({
    next: () => {
      console.log('Deleted user:', user);
      this.loadUsers();
    }
  });
}
}
