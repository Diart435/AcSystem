package com.example.AcSystemProducer.Service;

import com.example.AcSystemProducer.DTO.CompanyKafkaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CompanyKafkaProducer {
    private final KafkaTemplate<String, CompanyKafkaDTO> kafkaTemplate;

    public CompanyKafkaProducer(KafkaTemplate<String, CompanyKafkaDTO> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCompanyToKafka(CompanyKafkaDTO companyKafkaDTO){
        kafkaTemplate.send("companies", companyKafkaDTO);
        log.info("Company sent: {}",companyKafkaDTO.getName());
    }
}
