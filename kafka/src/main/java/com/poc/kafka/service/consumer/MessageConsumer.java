package com.poc.kafka.service.consumer;


import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.poc.kafka.model.KafkaConstant.KAFKA_TOPIC;


@Service
public class MessageConsumer {

    @Getter
    private final List<String> consumedMessages = new ArrayList<>();
    private LocalDateTime localDateTime;
    private LocalDateTime startTime;
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = KAFKA_TOPIC, groupId = "performance-group")
    public void listenGroup1(String message) {
        if (localDateTime == null) {
            startTime = LocalDateTime.now();
        }
        consumedMessages.add(message);
        localDateTime = LocalDateTime.now();
        logger.info("\n Timestamp: {} Consumer 1 received message: {}", localDateTime, message);
    }

    @KafkaListener(topics = KAFKA_TOPIC, groupId = "performance-group")
    public void listenGroup2(String message) {
        if (localDateTime == null) {
            startTime = LocalDateTime.now();
        }
        consumedMessages.add(message);
        localDateTime = LocalDateTime.now();
        logger.info("\n Timestamp: {} Consumer 2 received message: {}", localDateTime, message);
    }

    @KafkaListener(topics = KAFKA_TOPIC, groupId = "performance-group")
    public void listenGroup3(String message) {
        if (localDateTime == null) {
            startTime = LocalDateTime.now();
        }
        consumedMessages.add(message);
        localDateTime = LocalDateTime.now();
        logger.info("\n Timestamp: {} Consumer 3 received message: {}", localDateTime, message);
    }

    public ResponseEntity<String> getMessageCount(){
        return ResponseEntity.ok("Message-count : " + consumedMessages.size());
    }

    public ResponseEntity<String> getTotalConsumptionTime(){
        Duration duration = Duration.between(startTime, localDateTime);
        return ResponseEntity.ok("Total time consumption from consumers end : " + duration.toMillis() + " ms");
    }
}

