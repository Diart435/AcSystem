package com.example.AcSystem.DTO;

import lombok.Data;

@Data
public class CompanyResponse {
    private String name;
    private String phone;
    private String ceo;
    private String[] users;

}
