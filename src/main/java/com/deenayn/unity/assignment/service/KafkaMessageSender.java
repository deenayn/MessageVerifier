package com.deenayn.unity.assignment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "messages";

    public KafkaMessageSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        log.info("Sending message to Kafka topic {}, message = {}", TOPIC, message);
        kafkaTemplate.send(TOPIC, message);
    }
}
