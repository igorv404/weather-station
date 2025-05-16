package io.igorv404.weather_station.dto.response;

import java.time.Instant;

public record MeasurementDto(double value, Instant timestamp) {}
