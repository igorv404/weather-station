package io.igorv404.weather_station.controller;

import io.igorv404.weather_station.dto.request.AuthRequest;
import io.igorv404.weather_station.dto.response.AuthResponse;
import io.igorv404.weather_station.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {
    return new ResponseEntity<>(customerService.register(authRequest), HttpStatus.CREATED);
  }
}
