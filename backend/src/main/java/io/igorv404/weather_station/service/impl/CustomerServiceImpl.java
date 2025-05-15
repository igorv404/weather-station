package io.igorv404.weather_station.service.impl;

import io.igorv404.weather_station.dto.request.AuthRequest;
import io.igorv404.weather_station.dto.response.AuthResponse;
import io.igorv404.weather_station.model.Customer;
import io.igorv404.weather_station.repository.CustomerRepository;
import io.igorv404.weather_station.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;

  @Override
  public Customer findByEmail(String email) {
    return customerRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(String.format("Customer with email %s not found", email)));
  }

  @Override
  public AuthResponse register(AuthRequest authRequest) {
    Customer customer = Customer.builder()
        .email(authRequest.email())
        .password(authRequest.password())
        .build();

    Customer entity = customerRepository.save(customer);

    return new AuthResponse("", entity.getEmail());
  }

  @Override
  public AuthResponse login(AuthRequest authRequest) {
    return null;
  }
}
