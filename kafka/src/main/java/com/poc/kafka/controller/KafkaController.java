package com.poc.kafka.controller;

import com.poc.kafka.service.consumer.MessageConsumer;
import com.poc.kafka.service.producer.MessageProducer;
import com.poc.kafka.service.PerformanceTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/kafka")
public class KafkaController {

        @Autowired
        private MessageProducer kafkaMessageProducer;

        @Autowired
        private PerformanceTesting performanceTesting;

        @Autowired
        private MessageConsumer messageConsumer;

        @PostMapping("/publish")
        public void publishMessage(@RequestBody String message) {
                kafkaMessageProducer.sendMessage(message);
        }

        @GetMapping("/consumed")
        public List<String> getConsumedMessages() {
                return messageConsumer.getConsumedMessages();
        }

        @GetMapping("/message-count")
        public ResponseEntity<String>  getConsumedMessagesCount() {
                return messageConsumer.getMessageCount();
        }

        @GetMapping("/time-consumption")
        public ResponseEntity<String>  getTotalTimeConsumption() {
                return messageConsumer.getTotalConsumptionTime();
        }

        @PostMapping("/perf-test")
        public ResponseEntity<String> testSingleTopicSingleConsumerGroup(@RequestBody String message) {
                return performanceTesting.perfTest(message);
        }
}
