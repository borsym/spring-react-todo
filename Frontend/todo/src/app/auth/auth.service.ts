import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loggedIn = false;

  constructor() {
    const storedLoginState = localStorage.getItem('isLoggedIn');
    if (storedLoginState) {
      this.loggedIn = JSON.parse(storedLoginState);
    }
  }

  login() {
    this.loggedIn = true;
    localStorage.setItem('isLoggedIn', JSON.stringify(this.loggedIn));
  }

  logout() {
    this.loggedIn = false;
    localStorage.removeItem('isLoggedIn');
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }
}
