package io.igorv404.weather_station.repository;

import io.igorv404.weather_station.model.Measurement;
import io.igorv404.weather_station.model.WeatherStation;
import io.igorv404.weather_station.model.enums.MeasurementTypes;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {
  List<Measurement> findAllByWeatherStationAndMeasurementType(WeatherStation weatherStation, MeasurementTypes measurementType);

  List<Measurement> findAllByWeatherStation(WeatherStation weatherStation);
}
