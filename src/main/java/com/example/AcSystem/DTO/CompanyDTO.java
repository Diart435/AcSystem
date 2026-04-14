package com.example.AcSystem.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
}
