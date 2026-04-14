package com.example.AcSystem.KafkaConfig;

import com.example.AcSystem.JSON.KafkaJsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import com.example.AcSystem.Entity.Company;
import org.springframework.kafka.core.ProducerFactory;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Company> producerFactory(ObjectMapper objectMapper){
        Map<String, Object> confProperties = new HashMap<>();
        confProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        confProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        DefaultKafkaProducerFactory<String, Company> factory = new DefaultKafkaProducerFactory<>(confProperties);

        factory.setValueSerializer(new KafkaJsonSerializer<>(objectMapper));
        return factory;
    }

    @Bean
    public KafkaTemplate<String, Company> kafkaTemplate(ProducerFactory<String, Company> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
