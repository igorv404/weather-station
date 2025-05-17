#include "sensors_data.h"
#include "time.h"

float getTemperature() { return random(200, 300) / 10.0; }
float getHumidity()    { return random(300, 800) / 10.0; }
float getPressure()    { return random(9500, 10500) / 10.0; }
float getCO2()         { return random(400, 1200); }

String getIsoTimestamp() {
  time_t now;
  time(&now);

  char buf[25];
  strftime(buf, sizeof(buf), "%Y-%m-%dT%H:%M:%SZ", gmtime(&now));
  
  return String(buf);
}

SensorsData collectSensorsData() {
  SensorsData data;

  data.temperature = getTemperature();
  data.humidity = getHumidity();
  data.pressure = getPressure();
  data.co2 = getCO2();
  data.timestamp = getIsoTimestamp();
  data.weatherStationId = 4;

  return data;
}
