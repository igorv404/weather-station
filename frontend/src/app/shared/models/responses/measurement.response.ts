export interface Measurement {
  value: number;
  timestamp: Date;
}

export interface Measurements {
  temperatures: Measurement[];
  humidity: Measurement[];
  pressures: Measurement[];
  co2: Measurement[];
}
