package com.poc.kafka.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.poc.kafka.model.KafkaConstant.KAFKA_TOPIC;

@Configuration
public class KafkaConfig {

    @Bean
    public AdminClient adminClient() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:39092");
        return AdminClient.create(config);
    }

    @Bean
    public void createTopic() {
        try {
            AdminClient adminClient = adminClient();
            NewTopic newTopic = new NewTopic(KAFKA_TOPIC, 3, (short) 1);
            adminClient.createTopics(List.of(newTopic));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
