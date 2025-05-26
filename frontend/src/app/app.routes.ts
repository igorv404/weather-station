import { Routes } from '@angular/router';
import { RegistrationComponent } from './features/auth/registration/registration.component';
import { LoginComponent } from './features/auth/login/login.component';
import {HomeComponent} from './features/home/home.component';
import {WeatherStationComponent} from './features/weather-station/weather-station.component';

export const routes: Routes = [
  {path: '', pathMatch: 'full', component: HomeComponent},
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'weather-station/:id', component: WeatherStationComponent },
];
