package com.poc.kafka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.kafka.model.Event;

public class BotIdRetriever {
    public static String extractBotId(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Event event = objectMapper.readValue(message, Event.class);
            String botId = event.getBotId();
            System.out.println("Bot ID: " + botId);
            return botId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
