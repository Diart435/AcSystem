package com.example.AcSystemProducer.DTO;

import com.example.AcSystemProducer.Entity.DedupKey;
import lombok.Data;

@Data
public class CompanyKafkaDTO implements DedupKey {
    private String name;
    private String phone;
    private String ceo;
    private String[] users;

    @Override
    public String getDedupKey(){
        return name;
    }
}
