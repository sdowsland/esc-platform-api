package io.github.esc.platform.web.rest;

import io.github.esc.platform.service.EscPlatformKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/esc-platform-kafka")
public class EscPlatformKafkaResource {

    private final Logger log = LoggerFactory.getLogger(EscPlatformKafkaResource.class);

    private EscPlatformKafkaProducer kafkaProducer;

    public EscPlatformKafkaResource(EscPlatformKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
