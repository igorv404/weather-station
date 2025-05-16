package io.igorv404.weather_station.controller;

import io.igorv404.weather_station.dto.request.RegisterWeatherStation;
import io.igorv404.weather_station.dto.response.WeatherStationDto;
import io.igorv404.weather_station.service.WeatherStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather-stations")
@RequiredArgsConstructor
public class WeatherStationController {
  private final WeatherStationService weatherStationService;

  @PostMapping
  public ResponseEntity<WeatherStationDto> register(@RequestBody RegisterWeatherStation registerWeatherStation) {
    return new ResponseEntity<>(weatherStationService.register(registerWeatherStation), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> updateName(@PathVariable long id, @RequestParam String name) {
    weatherStationService.updateName(id, name);

    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    weatherStationService.delete(id);

    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
