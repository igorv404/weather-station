#include <Arduino.h>
#include "wifi_manager.h"

void setup() {
  Serial.begin(115200);
  connectToWiFi();
}

void loop() {
}
