package com.onurdemir.playground.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @KafkaListener(topics = "playground-topic", groupId = "playground-group")
    public void listen(String message) {
        System.out.println("Received: " + message);
    }
}
