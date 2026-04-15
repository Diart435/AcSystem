package com.example.AcSystemProducer.Mapper;

import com.example.AcSystemProducer.DTO.CompanyKafkaDTO;
import com.example.AcSystemProducer.DTO.CompanyResponse;
import com.example.AcSystemProducer.Entity.Company;
import com.example.AcSystemProducer.Entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    CompanyResponse toCompanyResponse(Company company);
    CompanyKafkaDTO toKafkaCompany(Company company);

    default String[] toStringUsers(List<User> users){
        if(users != null) {
            return users.stream().map(User::getLogin).toArray(String[]::new);
        }
        else {
            return null;
        }
    }

    default String toStringCeo(User ceo){
        if(ceo != null){
            return ceo.getLogin();
        }
        else{
            return null;
        }
    }
}
