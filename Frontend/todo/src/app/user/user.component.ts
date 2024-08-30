import { Component } from '@angular/core';
import { DUMMY_USERS } from '../../dummy-user-data';
import { RouterLink, RouterModule } from '@angular/router';
import { UserService } from './service/user.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [RouterLink, RouterModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {
  constructor(private userService: UserService) {}
  get users() {
    return this.userService.getUsers;
  }
}
