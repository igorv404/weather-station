package io.igorv404.weather_station.service;

import io.igorv404.weather_station.dto.request.RegisterWeatherStation;
import io.igorv404.weather_station.dto.response.WeatherStationDto;
import io.igorv404.weather_station.model.WeatherStation;

public interface WeatherStationService {
  WeatherStation findById(long id);

  WeatherStationDto register(RegisterWeatherStation registerWeatherStation);

  void updateName(long id, String name);

  void delete(long id);
}
