package com.example.AcSystemProducer.Service;

import com.example.AcSystemProducer.Entity.Company;
import com.example.AcSystemProducer.Entity.User;
import com.example.AcSystemProducer.Exception.CompanyAlreadyExistsException;
import com.example.AcSystemProducer.Mapper.CompanyMapper;
import com.example.AcSystemProducer.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyKafkaProducer companyKafkaProducer;
    private final CompanyMapper companyMapper;
    @Transactional
    public Company createCompany(String name, String phone){
        try {
            Company company = new Company(name, phone);
            log.info("company created");
            companyKafkaProducer.sendCompanyToKafka(companyMapper.toKafkaCompany(company));
            return companyRepository.save(company);
        } catch (DataIntegrityViolationException e) {
            throw new CompanyAlreadyExistsException("Company already exists");
        }
    }

    @Transactional(readOnly = true)
    public Company getCompany(String name){
        Optional<Company> company = companyRepository.findByName(name);
        return company.orElse(null);
    }

    public void addCeo(Company company, User user){
        company.setCeo(user);
    }

    public void addUser(Company company, User user){
        if (company.getUsers() == null) {
            company.setUsers(new ArrayList<>());
        }
        company.getUsers().add(user);
    }

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

}
