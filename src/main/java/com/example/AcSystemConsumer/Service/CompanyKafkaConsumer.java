package com.example.AcSystemConsumer.Service;

import com.example.AcSystemConsumer.DTO.CompanyKafkaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CompanyKafkaConsumer {

    @KafkaListener(topics = "companies",groupId = "company-group",containerFactory = "containerFactory")
    public void consumeCompany(CompanyKafkaDTO companyKafkaDTO){
        log.info("Company recieved: {}", companyKafkaDTO.getName());
    }
}
