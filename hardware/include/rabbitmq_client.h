#pragma once

extern const char* MEASUREMENT_QUEUE;
extern const char* DLQ_QUEUE;

void setupRabbitMQ();
void loopRabbitMQ();
