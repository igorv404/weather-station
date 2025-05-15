package io.igorv404.weather_station.service.impl;

import io.igorv404.weather_station.dto.request.RegisterWeatherStation;
import io.igorv404.weather_station.dto.response.WeatherStationDto;
import io.igorv404.weather_station.model.Customer;
import io.igorv404.weather_station.model.WeatherStation;
import io.igorv404.weather_station.repository.WeatherStationRepository;
import io.igorv404.weather_station.service.CustomerService;
import io.igorv404.weather_station.service.WeatherStationService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherStationServiceImpl implements WeatherStationService {
  private final WeatherStationRepository weatherStationRepository;
  private final CustomerService customerService;

  @Override
  public WeatherStationDto register(RegisterWeatherStation registerWeatherStation) {
    if (weatherStationRepository.existsById(registerWeatherStation.id())) {
      throw new EntityExistsException(String.format("There is already a weather station with id %d", registerWeatherStation.id()));
    }

    Customer customer = customerService.findByEmail(registerWeatherStation.email());

    WeatherStation entity = new WeatherStation(registerWeatherStation.id(),
        registerWeatherStation.name(), customer);

    weatherStationRepository.save(entity);

    return new WeatherStationDto(entity);
  }
}
