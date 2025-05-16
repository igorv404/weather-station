package io.igorv404.weather_station.repository;

import io.igorv404.weather_station.model.Customer;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
  Optional<Customer> findByEmail(String email);
}
