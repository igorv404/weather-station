package io.igorv404.weather_station.repository;

import io.igorv404.weather_station.model.Customer;
import io.igorv404.weather_station.model.WeatherStation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherStationRepository extends JpaRepository<WeatherStation, Long> {
  List<WeatherStation> findAllByCustomer(Customer customer);
}
