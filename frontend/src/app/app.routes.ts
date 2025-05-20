import { Routes } from '@angular/router';
import { RegistrationComponent } from './features/auth/registration/registration.component';
import { LoginComponent } from './features/auth/login/login.component';

export const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent }
];
