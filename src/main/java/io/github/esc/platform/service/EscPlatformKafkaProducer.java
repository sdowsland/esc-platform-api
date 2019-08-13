package io.github.esc.platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EscPlatformKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(EscPlatformKafkaProducer.class);
    private static final String TOPIC = "topic_escplatform";

    private KafkaTemplate<String, String> kafkaTemplate;

    public EscPlatformKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        log.info("Producing message to {} : {}", TOPIC, message);
        this.kafkaTemplate.send(TOPIC, message);
    }
}
