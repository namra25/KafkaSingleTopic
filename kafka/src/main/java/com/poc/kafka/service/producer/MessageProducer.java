package com.poc.kafka.service.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.poc.kafka.model.KafkaConstant.KAFKA_TOPIC;
import static com.poc.kafka.util.BotIdRetriever.extractBotId;

@Service
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        String botId = extractBotId(message);
        this.kafkaTemplate.send(KAFKA_TOPIC, botId, message);
        LocalDateTime timestamp = LocalDateTime.now();
        logger.info("\n Timestamp: {} Message sent : {}", timestamp, message);
    }
}
