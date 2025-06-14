package io.igorv404.weather_station.service.impl;

import io.igorv404.weather_station.config.RabbitMQConfig;
import io.igorv404.weather_station.dto.request.MeasurementMessage;
import io.igorv404.weather_station.dto.response.MeasurementDto;
import io.igorv404.weather_station.dto.response.MeasurementResponse;
import io.igorv404.weather_station.dto.response.enums.MeasurementResponseTypes;
import io.igorv404.weather_station.model.Measurement;
import io.igorv404.weather_station.model.WeatherStation;
import io.igorv404.weather_station.model.enums.MeasurementTypes;
import io.igorv404.weather_station.repository.MeasurementRepository;
import io.igorv404.weather_station.service.MeasurementService;
import io.igorv404.weather_station.service.WeatherStationService;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
  private final MeasurementRepository measurementRepository;
  private final WeatherStationService weatherStationService;

  @Override
  @RabbitListener(queues = RabbitMQConfig.MEASUREMENT_QUEUE)
  public void receiveMeasurement(MeasurementMessage message) {
    try {
      WeatherStation weatherStation = weatherStationService.findById(message.weatherStation());

      Instant timestamp = message.timestamp();

      List<Measurement> measurements = List.of(
          buildMeasurement(MeasurementTypes.TEMPERATURE, message.temperature(), timestamp,
              weatherStation),
          buildMeasurement(MeasurementTypes.HUMIDITY, message.humidity(), timestamp,
              weatherStation),
          buildMeasurement(MeasurementTypes.PRESSURE, message.pressure(), timestamp,
              weatherStation),
          buildMeasurement(MeasurementTypes.CO2, message.co2(), timestamp, weatherStation)
      );

      measurementRepository.saveAll(measurements);
    } catch (EntityNotFoundException e) {
      throw new AmqpRejectAndDontRequeueException(
          String.format("Failed to process measurement: weather station with id %d not found",
              message.weatherStation()), e);
    }
  }

  private Measurement buildMeasurement(MeasurementTypes type, double value, Instant timestamp,
      WeatherStation station) {
    return Measurement.builder()
        .measurementType(type)
        .value(value)
        .timestamp(timestamp)
        .weatherStation(station)
        .build();
  }

  @Override
  public MeasurementResponse getMeasurement(MeasurementResponseTypes type, LocalDate from,
      LocalDate to, Long weatherStationId) {
    WeatherStation weatherStation = weatherStationService.findById(weatherStationId);

    Instant now = Instant.now();
    Instant fromInstant;
    Instant toInstant;

    switch (type) {
      case LAST_WEEK -> {
        fromInstant = now.minus(7, ChronoUnit.DAYS);
        toInstant = now;
      }
      case LAST_MONTH -> {
        fromInstant = now.minus(30, ChronoUnit.DAYS);
        toInstant = now;
      }
      case CUSTOM -> {
        if (from == null || to == null) {
          throw new IllegalArgumentException("For CUSTOM type, 'from' and 'to' must be provided");
        }

        fromInstant = from.atStartOfDay(ZoneOffset.UTC).toInstant();
        toInstant = to.plusDays(2).atStartOfDay(ZoneOffset.UTC).toInstant();
      }
      default -> throw new IllegalArgumentException("Unknown type: " + type);
    }

    return new MeasurementResponse(
        getMeasurementsOfType(weatherStation, MeasurementTypes.TEMPERATURE, fromInstant, toInstant),
        getMeasurementsOfType(weatherStation, MeasurementTypes.HUMIDITY, fromInstant, toInstant),
        getMeasurementsOfType(weatherStation, MeasurementTypes.PRESSURE, fromInstant, toInstant),
        getMeasurementsOfType(weatherStation, MeasurementTypes.CO2, fromInstant, toInstant)
    );
  }

  private List<MeasurementDto> getMeasurementsOfType(WeatherStation station, MeasurementTypes type,
      Instant from, Instant to) {
    return measurementRepository
        .findAllByWeatherStationAndMeasurementTypeAndTimestampBetween(station, type, from, to)
        .stream()
        .map(m -> new MeasurementDto(m.getValue(), m.getTimestamp()))
        .toList();
  }
}
