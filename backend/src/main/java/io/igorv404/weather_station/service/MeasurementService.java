package io.igorv404.weather_station.service;

import io.igorv404.weather_station.dto.request.MeasurementMessage;
import io.igorv404.weather_station.dto.response.MeasurementResponse;
import io.igorv404.weather_station.dto.response.enums.MeasurementResponseTypes;
import java.time.LocalDate;

public interface MeasurementService {
  void receiveMeasurement(MeasurementMessage measurementMessage);

  MeasurementResponse getMeasurement(MeasurementResponseTypes type, LocalDate from, LocalDate to, Long weatherStation);
}
