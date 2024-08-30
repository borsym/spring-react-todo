import { Component, inject, input, OnDestroy, OnInit } from '@angular/core';
import {
  ActivatedRoute,
  ActivatedRouteSnapshot,
  ResolveFn,
  RouterStateSnapshot,
} from '@angular/router';
import { UserService } from '../service/user.service';
import { of, Subscription } from 'rxjs';

@Component({
  selector: 'app-user-details',
  standalone: true,
  imports: [],
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css',
})
export class UserDetailsComponent implements OnInit, OnDestroy {
  userName: string | undefined;
  private routeSub: Subscription = new Subscription();

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // console.log(this.route.snapshot.data['user']);
    // Access the resolved data
    this.routeSub = this.route.data.subscribe((data) => {
      this.userName = data['user']?.name;
      console.log(this.userName);
    });
  }

  ngOnDestroy(): void {
    // Clean up subscription when component is destroyed
    if (this.routeSub) {
      this.routeSub.unsubscribe();
    }
  }
}
export const resolveUserName: ResolveFn<
  { id: string; name: string } | undefined
> = (route: ActivatedRouteSnapshot, routerState: RouterStateSnapshot) => {
  const userService = inject(UserService);

  const userId = route.paramMap.get('userId'); // TODO pipe here
  return of(userService.userById(userId));
};
