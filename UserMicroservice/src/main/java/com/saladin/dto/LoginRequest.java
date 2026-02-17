package com.saladin.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;    // Change from username to email
    private String password;
}
