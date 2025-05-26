package io.igorv404.weather_station.service.impl;

import io.igorv404.weather_station.dto.request.RegisterWeatherStation;
import io.igorv404.weather_station.dto.response.WeatherStationDto;
import io.igorv404.weather_station.model.Customer;
import io.igorv404.weather_station.model.Measurement;
import io.igorv404.weather_station.model.WeatherStation;
import io.igorv404.weather_station.repository.MeasurementRepository;
import io.igorv404.weather_station.repository.WeatherStationRepository;
import io.igorv404.weather_station.service.CustomerService;
import io.igorv404.weather_station.service.WeatherStationService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherStationServiceImpl implements WeatherStationService {
  private final WeatherStationRepository weatherStationRepository;
  private final CustomerService customerService;
  private final MeasurementRepository measurementRepository;

  @Override
  public WeatherStation findById(long id) {
    return weatherStationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
        String.format("Weather station with id %d not found", id)));
  }

  @Override
  public List<WeatherStationDto> findAllCustomersWeatherStations(String customerEmail) {
    Customer customer = customerService.findByEmail(customerEmail);

    return weatherStationRepository.findAllByCustomer(customer).stream()
        .map(ws -> new WeatherStationDto(ws.getId(), ws.getName()))
        .toList();
  }

  @Override
  public WeatherStationDto register(RegisterWeatherStation registerWeatherStation) {
    if (weatherStationRepository.existsById(registerWeatherStation.id())) {
      throw new EntityExistsException(String.format("There is already a weather station with id %d",
          registerWeatherStation.id()));
    }

    Customer customer = customerService.findByEmail(registerWeatherStation.email());

    WeatherStation entity = new WeatherStation(registerWeatherStation.id(),
        registerWeatherStation.name(), customer);

    weatherStationRepository.save(entity);

    return new WeatherStationDto(entity);
  }

  @Override
  public void updateName(long id, String name) {
    WeatherStation entity = findById(id);

    entity.setName(name);

    weatherStationRepository.save(entity);
  }

  @Override
  public void delete(long id) {
    WeatherStation entity = findById(id);

    List<Measurement> measurements = measurementRepository.findAllByWeatherStation(entity);
    measurementRepository.deleteAll(measurements);

    weatherStationRepository.delete(entity);
  }
}
