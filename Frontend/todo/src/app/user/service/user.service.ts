import { Injectable } from '@angular/core';
import { DUMMY_USERS } from '../../../dummy-user-data';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private users = DUMMY_USERS;

  userById(id: string | null) {
    if (id === null) {
      throw new Error('not good');
    }
    return this.users.find((u) => u.id === id);
  }

  get getUsers() {
    return this.users;
  }
}
