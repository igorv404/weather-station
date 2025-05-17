#include <Arduino.h>
#include "wifi_manager.h"
#include "rabbitmq_client.h"

void setup() {
  Serial.begin(115200);
  connectToWiFi();
  setupRabbitMQ();
}

void loop() {
  loopRabbitMQ();
}
