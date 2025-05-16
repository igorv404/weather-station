package io.igorv404.weather_station.model;

import io.igorv404.weather_station.model.enums.MeasurementTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private MeasurementTypes measurementType;

  @Column(nullable = false)
  private double value;

  @Column(nullable = false)
  private Instant timestamp;

  @ManyToOne(optional = false)
  private WeatherStation weatherStation;
}
