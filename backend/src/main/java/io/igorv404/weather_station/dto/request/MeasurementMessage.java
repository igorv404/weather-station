package io.igorv404.weather_station.dto.request;

import java.time.Instant;

public record MeasurementMessage(double temperature, double humidity, double pressure, double co2,
                                 Instant timestamp, long weatherStation) {}
