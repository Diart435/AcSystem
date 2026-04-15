package com.example.AcSystemConsumer.Service;

import com.example.AcSystemConsumer.DTO.CompanyKafkaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyKafkaConsumer {
    private final DeduplicationService deduplicationService;

    @KafkaListener(topics = "companies", containerFactory = "containerFactory")
    public void consumeCompany(ConsumerRecord<String, CompanyKafkaDTO> record){
        String key = record.key();

        if(deduplicationService.isDuplicate(key)){
           log.warn("Duplicate company: {}", record.key());
           return;
        }

        try{
            deduplicationService.markAsProcessed(key);
            log.info("Successfully processed company: {}", key);
        }
        catch (Exception e){
            log.error("Failed to process company");
            throw e;
        }

    }
}
