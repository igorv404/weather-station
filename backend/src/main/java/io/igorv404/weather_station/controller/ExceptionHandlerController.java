package io.igorv404.weather_station.controller;

import io.igorv404.weather_station.dto.response.exception.ExceptionHandlerResponse;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
  @ExceptionHandler(value = {EntityNotFoundException.class})
  private ResponseEntity<ExceptionHandlerResponse> handleEntityNotFoundException(EntityNotFoundException e) {
    return new ResponseEntity<>(new ExceptionHandlerResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now()), HttpStatus.NOT_FOUND);
  }
}
