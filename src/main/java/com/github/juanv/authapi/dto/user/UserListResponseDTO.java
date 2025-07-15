package com.github.juanv.authapi.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class UserListResponseDTO {
	
    private List<DummyUserDTO> users;
    
}