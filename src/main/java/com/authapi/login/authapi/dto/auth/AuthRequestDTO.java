package com.authapi.login.authapi.dto.auth;

import lombok.Data;

@Data
public class AuthRequestDTO {
	
    private String username;
    
    private String password;
}
