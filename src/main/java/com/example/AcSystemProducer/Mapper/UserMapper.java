package com.example.AcSystemProducer.Mapper;


import com.example.AcSystemProducer.DTO.CompanyResponse;
import com.example.AcSystemProducer.DTO.UserResponse;
import com.example.AcSystemProducer.Entity.Company;
import com.example.AcSystemProducer.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "company", target = "companyResponse")
    UserResponse toUserResponse(User user);

    default CompanyResponse companyToResponse(Company company){
        if(company == null){
            return null;
        }
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setName(company.getName());
        companyResponse.setPhone(company.getPhone());
        if(company.getCeo() != null){
            companyResponse.setCeo(company.getCeo().getLogin());
        }
        if(company.getUsers() != null) {
            companyResponse.setUsers(company.getUsers().stream().map(User::getLogin).toArray(String[]::new));
        }
        return companyResponse;
    }
}
