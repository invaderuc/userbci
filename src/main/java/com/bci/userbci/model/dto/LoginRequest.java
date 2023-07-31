package com.bci.userbci.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginRequest {

    @Email
    @NotEmpty(message = "Email obligatorio")
    private String email;

    @NotEmpty(message = "Contraseña obligatorio")
    private String password;
}
