package com.pmu.coursesmanager.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pmu.coursesmanager.exception.CourseException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "course-topic";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(Object messageObject) {
        // Convert an object to JSON string
        String message;
        try {
            objectMapper.registerModule(new JavaTimeModule());
            message = objectMapper.writeValueAsString(messageObject);
        } catch (JsonProcessingException e) {
            throw new CourseException("la course et leur  partants ont été créé dans la base de données mais le message n'est pas envoyé au Kafka topic ", e);
        }
        // send to Kafka
        kafkaTemplate.send(TOPIC, message);
    }
}