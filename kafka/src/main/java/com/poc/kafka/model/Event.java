package com.poc.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
        private String eventId;
        private String botId;
        private String spaceId;
        private String userName;
        private String eventName;
        private String email;
        private String endpoint;
        private String serviceId;
        private String timestamp;
}
