package com.example.AcSystem.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    private boolean ceo;

    private String nameCompany;
}
