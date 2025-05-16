package io.igorv404.weather_station.dto.response;

import java.util.List;

public record MeasurementResponse(List<MeasurementDto> temperatures, List<MeasurementDto> humidity,
                                  List<MeasurementDto> pressures, List<MeasurementDto> co2) {}
