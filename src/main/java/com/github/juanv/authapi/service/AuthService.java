package com.github.juanv.authapi.service;

import com.github.juanv.authapi.dto.auth.AuthRequestDTO;
import com.github.juanv.authapi.dto.user.UserInfoDTO;

public interface AuthService {
	
    UserInfoDTO loginAndLog(AuthRequestDTO authRequestDTO);
}
