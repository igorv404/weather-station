import pika
import json
import time
from datetime import datetime, timezone
import random

RABBITMQ_HOST = 'localhost'
QUEUE_NAME = 'measurement.queue'

credentials = pika.PlainCredentials('alien', 'rootroot')
parameters = pika.ConnectionParameters(host=RABBITMQ_HOST, credentials=credentials)

connection = pika.BlockingConnection(parameters)
channel = connection.channel()

def send_measurement():
    message = {
        "temperature": round(random.uniform(18.0, 25.0), 2),
        "humidity": round(random.uniform(40.0, 60.0), 2),
        "pressure": round(random.uniform(1000.0, 1020.0), 2),
        "co2": round(random.uniform(400.0, 800.0), 2),
        "timestamp": datetime.now(timezone.utc).isoformat(),
        "weatherStation": 4
    }

    channel.basic_publish(
        exchange='',
        routing_key=QUEUE_NAME,
        body=json.dumps(message),
        properties=pika.BasicProperties(content_type='application/json')
    )

    print("Sent:", message)

try:
    while True:
        send_measurement()
        time.sleep(1)
except KeyboardInterrupt:
    print("Stopped")
finally:
    connection.close()
