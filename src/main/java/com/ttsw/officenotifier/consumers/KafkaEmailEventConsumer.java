package com.ttsw.officenotifier.consumers;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaEmailEventConsumer {

    @KafkaListener(topics = "${office-core.kafka.topics.email.name}", groupId = "office-notifier-group")
    public void consumeEvent(@Payload String message) {
        System.out.println(String.format("Content: %s", message));
    }
}
