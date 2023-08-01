package com.bci.userbci.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResponse {

    private String message;
    private LocalDateTime fecha;

    public MessageResponse(String message) {
        this.message = message;
        this.fecha = LocalDateTime.now();
    }
}
