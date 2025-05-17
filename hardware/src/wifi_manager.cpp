#include <Arduino.h>
#include <wifi_manager.h>
#include <WiFiManager.h>

void connectToWiFi() {
  WiFiManager wm;

  bool connected = wm.autoConnect("Weather station", "rootroot");

  if (!connected) {
    delay(10000);
    ESP.restart();
  }
}
