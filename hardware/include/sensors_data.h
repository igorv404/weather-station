#pragma once
#include <Arduino.h>

struct SensorsData {
  float temperature;
  float humidity;
  float pressure;
  float co2;
  String timestamp;
  long weatherStationId;
};

SensorsData collectSensorsData();
