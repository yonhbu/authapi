package com.authapi.login.authapi.dto.user;

import lombok.Data;

@Data
public class DummyUserDTO {
	
    private int id;
    
    private String username;
    
    private String email;
    
    private String firstName;
    
    private String lastName;

    private String gender;

    private String image;

    private String accessToken;
    
    private String refreshToken;
    
 
}