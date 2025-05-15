package io.igorv404.weather_station.repository;

import io.igorv404.weather_station.model.Measurement;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {}
