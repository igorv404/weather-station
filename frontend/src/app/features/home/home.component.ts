import { Component } from '@angular/core';
import {Button} from 'primeng/button';
import {RouterLink} from '@angular/router';
import {WeatherStation} from '../../shared/models/responses/weather-station.model';

@Component({
  selector: 'app-home',
  imports: [
    Button,
    RouterLink
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  protected stations: WeatherStation[] = [];
}
