import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
// just to mimic that the user can log in and guard a path
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): MaybeAsync<GuardResult> {
    const isLoggedIn = this.authService.isLoggedIn();
    if (state.url === '/') {
      if (isLoggedIn) {
        this.router.navigate(['/tasks']);
      } else {
        this.router.navigate(['/login']);
      }
      return false;
    }

    if (!isLoggedIn) {
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }
}
