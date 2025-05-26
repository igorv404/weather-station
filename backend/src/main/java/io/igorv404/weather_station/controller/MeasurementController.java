package io.igorv404.weather_station.controller;

import io.igorv404.weather_station.dto.response.MeasurementResponse;
import io.igorv404.weather_station.dto.response.enums.MeasurementResponseTypes;
import io.igorv404.weather_station.service.MeasurementService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
  private final MeasurementService measurementService;

  @GetMapping
  public ResponseEntity<MeasurementResponse> getMeasurements(
      @RequestParam MeasurementResponseTypes type,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
      @RequestParam Long weatherStation
  ) {
    return ResponseEntity.ok(measurementService.getMeasurement(type, from, to, weatherStation));
  }
}
