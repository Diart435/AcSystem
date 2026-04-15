package com.example.AcSystemProducer.Service;

import com.example.AcSystemProducer.DTO.Message;
import com.example.AcSystemProducer.Entity.DedupKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T extends DedupKey> void sendToKafka(Message<T> message){
        T data = message.data();

        String key = data.getDedupKey();

        ProducerRecord<String,Object> record = new ProducerRecord<>(
                message.topic(),
                key,
                data
        );

        record.headers().add("business-key", key.getBytes(StandardCharsets.UTF_8));
        record.headers().add("timestamp", String.valueOf(System.currentTimeMillis()).getBytes());
        kafkaTemplate.send(record);
        log.info("Data sent: {}",message.data());
    }
}
