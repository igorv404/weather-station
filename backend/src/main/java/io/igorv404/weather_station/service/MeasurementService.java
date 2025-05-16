package io.igorv404.weather_station.service;

import io.igorv404.weather_station.dto.request.MeasurementMessage;

public interface MeasurementService {
  void receiveMeasurement(MeasurementMessage measurementMessage);
}
