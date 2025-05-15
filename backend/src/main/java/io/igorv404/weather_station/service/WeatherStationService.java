package io.igorv404.weather_station.service;

import io.igorv404.weather_station.dto.request.RegisterWeatherStation;
import io.igorv404.weather_station.dto.response.WeatherStationDto;

public interface WeatherStationService {
  WeatherStationDto register(RegisterWeatherStation registerWeatherStation);
}
