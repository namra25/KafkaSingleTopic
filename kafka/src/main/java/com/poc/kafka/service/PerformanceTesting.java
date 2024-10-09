package com.poc.kafka.service;

import com.poc.kafka.service.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PerformanceTesting {

    @Autowired
    private MessageProducer messageProducer;

    private static final int MESSAGE_COUNT = 10000;
    private static final String message = """
            {
            "eventId": "12",
            "botId": "bot1",
            "spaceId": "123",
            "userName": "webex1",
            "eventName": "John Doe",
            "email": "john.doe@example.com",
            "endpoint": "https://www.server.com/TaskBot/bot/active",
            "serviceId": "serviceId1",
            "timestamp": "2024-09-20T12:00:00Z"
            }""";

    public ResponseEntity<String> perfTest(String message){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            messageProducer.sendMessage(message);
        }

        long duration = System.currentTimeMillis() - startTime;
        String result = "Total time consumption from producer end : " + duration + " ms";
        return ResponseEntity.ok(result);
    }

}
