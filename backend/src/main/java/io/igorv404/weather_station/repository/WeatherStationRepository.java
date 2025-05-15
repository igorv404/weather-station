package io.igorv404.weather_station.repository;

import io.igorv404.weather_station.model.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherStationRepository extends JpaRepository<WeatherStation, Long> {}
