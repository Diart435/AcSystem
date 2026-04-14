package com.example.AcSystem.Mapper;

import com.example.AcSystem.DTO.CompanyResponse;
import com.example.AcSystem.Entity.Company;
import com.example.AcSystem.Entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    CompanyResponse toCompanyResponse(Company company);

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
