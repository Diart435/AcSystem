package com.example.AcSystem.DTO;

import lombok.Data;

@Data
public class UserResponse {
    private String login;

    private CompanyResponse companyResponse;
}
