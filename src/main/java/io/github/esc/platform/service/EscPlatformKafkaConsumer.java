package io.github.esc.platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EscPlatformKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(EscPlatformKafkaConsumer.class);
    private static final String TOPIC = "topic_escplatform";

    @KafkaListener(topics = "topic_escplatform", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
