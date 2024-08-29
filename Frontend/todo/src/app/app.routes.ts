import { Routes } from '@angular/router';
import { TeamComponent } from './team/team.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { TaskComponent } from './task/task.component';
import { inject } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';

export const routes: Routes = [
  {
    path: '', // resolve.... TODO
    component: LoginComponent,
    pathMatch: 'full', // Ensures the full path is matched
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'tasks',
    component: TaskComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'teams',
    component: TeamComponent,
  },
];
