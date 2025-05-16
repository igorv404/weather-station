package io.igorv404.weather_station.service;

import io.igorv404.weather_station.dto.request.AuthRequest;
import io.igorv404.weather_station.dto.response.AuthResponse;
import io.igorv404.weather_station.model.Customer;

public interface CustomerService {
  Customer findByEmail(String email);

  AuthResponse register(AuthRequest authRequest);

  AuthResponse login(AuthRequest authRequest);
}
