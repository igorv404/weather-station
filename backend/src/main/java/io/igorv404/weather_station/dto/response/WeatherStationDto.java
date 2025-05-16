package io.igorv404.weather_station.dto.response;

import io.igorv404.weather_station.model.WeatherStation;

public record WeatherStationDto(long id, String name) {
  public WeatherStationDto(WeatherStation entity) {
    this(entity.getId(), entity.getName());
  }
}
