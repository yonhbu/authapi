package com.authapi.login.authapi.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class UserListResponseDTO {
	
    private List<DummyUserDTO> users;
    
    private int limit;
    
    private int total;
    
    private int skip;
    
}