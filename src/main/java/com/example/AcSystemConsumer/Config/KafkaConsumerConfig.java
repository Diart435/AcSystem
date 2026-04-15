package com.example.AcSystemConsumer.Config;

import com.example.AcSystemConsumer.DTO.CompanyKafkaDTO;
import com.example.AcSystemConsumer.JSON.KafkaJsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, CompanyKafkaDTO> consumerFactory(ObjectMapper objectMapper){
        Map<String, Object> confProperties = new HashMap<>();
        confProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        confProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "company-group");
        confProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        DefaultKafkaConsumerFactory<String, CompanyKafkaDTO> factory = new DefaultKafkaConsumerFactory<>(confProperties);
        factory.setValueDeserializer(new KafkaJsonDeserializer<>(objectMapper, CompanyKafkaDTO.class));

        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CompanyKafkaDTO> containerFactory(ConsumerFactory<String, CompanyKafkaDTO> consumerFactory){
        var containerFactory = new ConcurrentKafkaListenerContainerFactory<String, CompanyKafkaDTO>();
        containerFactory.setConcurrency(1);
        containerFactory.setConsumerFactory(consumerFactory);
        return containerFactory;
    }
}
