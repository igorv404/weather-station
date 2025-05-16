package io.igorv404.weather_station.dto.response.exception;

import java.time.Instant;

public record ExceptionHandlerResponse(String message, Integer statusCode, Instant timestamp) {}
