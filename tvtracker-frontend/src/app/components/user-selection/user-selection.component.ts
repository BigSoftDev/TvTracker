import { Component, Signal, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User, UserService } from '../../service/user.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';

@Component({
  selector: 'user-selection',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-selection.component.html',
  styleUrl: './user-selection.component.scss'
})
export class UserSelectionComponent {

  showNewUserModal = signal(false);

  newUsername = '';

    users: Signal<User[]> = toSignal(
    this.userService.getUsers(),
    { initialValue: [] }
    );
    




    constructor(private userService: UserService, private router: Router) { }

  openNewUserModal() {
    this.newUsername = '';
    this.showNewUserModal.set(true);
  }

  closeNewUserModal() {
    this.showNewUserModal.set(false);
  }

  saveUser() {
    const username = this.newUsername.trim();

    if (!username) {
      return;
    }

    console.log('Creating user:', username);

    this.userService.createUser(username).subscribe({
      next: (user) => {
        this.userService.setCurrentUser(user);
        this.selectUser(user);
      }
    });

    this.closeNewUserModal();
  }

    selectUser(user: User) {
    console.log('Selected user:', user);

    this.userService.setCurrentUser(user);
    this.router.navigate(['/my-shows']);
  }
}