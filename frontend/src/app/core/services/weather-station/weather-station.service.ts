import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {WeatherStation} from '../../../shared/models/responses/weather-station.response';

@Injectable({
  providedIn: 'root'
})
export class WeatherStationService {
  constructor(private http: HttpClient) {}

  public getAllCustomersWeatherStations(email: string) {
    return this.http.get<WeatherStation[]>('http://localhost:8080/weather-stations', {
      params: {
        email: email
      }
    });
  }
}
