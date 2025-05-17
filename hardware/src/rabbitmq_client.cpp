#include "rabbitmq_client.h"
#include <PubSubClient.h>
#include <WiFi.h>
#include "time.h"
#include <ArduinoJson.h>
#include "sensors_data.h"

const char* mqtt_server = "192.168.0.62";
const int mqtt_port = 1883;
const char* mqtt_user = "alien";
const char* mqtt_password = "rootroot";

const char* MEASUREMENT_QUEUE = "measurement.queue";
const char* DLQ_QUEUE = "measurement.queue.dlq";
const char* mqtt_topic = MEASUREMENT_QUEUE;

WiFiClient espClient;
PubSubClient client(espClient);

constexpr unsigned long interval = 60000;
unsigned long previousMillis = 0;

void reconnect() {
  while (!client.connected()) {
    String clientId = "WeatherStation-4";
    if (client.connect(clientId.c_str(), mqtt_user, mqtt_password)) delay(10000);
  }
}

void setupRabbitMQ() {
  client.setServer(mqtt_server, mqtt_port);
  configTime(0, 0, "pool.ntp.org", "time.nist.gov");
  reconnect();
}

void publishMeasurement(const SensorsData& data) {
  StaticJsonDocument<192> doc;
  doc["temperature"] = data.temperature;
  doc["humidity"] = data.humidity;
  doc["pressure"] = data.pressure;
  doc["co2"] = data.co2;
  doc["timestamp"] = data.timestamp;
  doc["weatherStation"] = data.weatherStationId;

  char buffer[192];
  serializeJson(doc, buffer);

  client.publish(mqtt_topic, buffer);
}

void loopRabbitMQ() {
  if (!client.connected()) reconnect();

  client.loop();

  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval) {
    previousMillis = currentMillis;

    SensorsData data = collectSensorsData();

    publishMeasurement(data);
  }
}
