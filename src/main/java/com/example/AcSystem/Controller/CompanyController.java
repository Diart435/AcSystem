package com.example.AcSystem.Controller;

import com.example.AcSystem.DTO.CompanyDTO;
import com.example.AcSystem.DTO.CompanyResponse;
import com.example.AcSystem.Entity.Company;
import com.example.AcSystem.Mapper.CompanyMapper;
import com.example.AcSystem.Service.CompanyService;
import com.example.AcSystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final UserService userService;
    private final CompanyMapper companyMapper;

    @GetMapping("/api/comps/{name}")
    public ResponseEntity<CompanyResponse> companyInfo(@PathVariable String name){
        Company company = companyService.getCompany(name);
        CompanyResponse companyResponse = companyMapper.toCompanyResponse(company);
        return ResponseEntity.ok(companyResponse);
    }

    @PostMapping("/api/createCompany")
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyDTO companyDTO){
        Company saved = companyService.createCompany(companyDTO.getName(), companyDTO.getPhone());
        CompanyResponse companyResponse = companyMapper.toCompanyResponse(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyResponse);
    }

    @GetMapping("/api/comps/all")
    public ResponseEntity<List<CompanyResponse>> getAll(){
        List<CompanyResponse> companies = companyService.getCompanies().stream().map(companyMapper::toCompanyResponse).toList();
        return ResponseEntity.ok(companies);
    }

}
