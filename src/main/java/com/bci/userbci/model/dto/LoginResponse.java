package com.bci.userbci.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LoginResponse {

    @NotEmpty(message = "Token")
    private UUID token;

    @NotEmpty(message = "Mensaje")
    private String message;
}
