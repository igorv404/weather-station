import {Component, OnInit} from '@angular/core';
import {Button} from 'primeng/button';
import {RouterLink} from '@angular/router';
import {WeatherStation} from '../../shared/models/responses/weather-station.response';
import {WeatherStationService} from '../../core/services/weather-station/weather-station.service';

@Component({
  selector: 'app-home',
  imports: [
    Button,
    RouterLink
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
  protected stations: WeatherStation[] = [];

  constructor(private weatherStationService: WeatherStationService) {}

  ngOnInit() {
    this.weatherStationService.getAllCustomersWeatherStations('test@test.com').subscribe((res) => {
      this.stations = res;
    });
  }
}
