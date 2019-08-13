package io.github.esc.platform.web.rest;

import io.github.esc.platform.EscPlatformApp;
import io.github.esc.platform.config.TestSecurityConfiguration;
import io.github.esc.platform.service.EscPlatformKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = {EscPlatformApp.class, TestSecurityConfiguration.class})
public class EscPlatformKafkaResourceIT {

    @Autowired
    private EscPlatformKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        EscPlatformKafkaResource kafkaResource = new EscPlatformKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/esc-platform-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
