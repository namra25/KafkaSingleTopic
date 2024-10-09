#!/bin/bash

# Function to wait for Kafka to be ready
wait_for_kafka() {
    local retries=200
    local count=0
    while [[ $count -lt $retries ]]; do
        if echo "exit" | nc kafka 9092; then
            echo "Kafka is ready"
            return
        fi
        echo "Waiting for Kafka..."
        sleep 1
        ((count++))
    done
    echo "Kafka is not ready after waiting."
    exit 1
}

# Wait for Kafka to be ready
wait_for_kafka

# Create the topic with 3 partitions
kafka-topics.sh --create --topic my_topic --bootstrap-server kafka:9092 --partitions 3 --replication-factor 1
