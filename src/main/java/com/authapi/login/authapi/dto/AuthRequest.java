package com.authapi.login.authapi.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
