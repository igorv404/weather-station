package io.igorv404.weather_station.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherStation {
  @Id
  private long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne(optional = false)
  private Customer customer;
}
