package com.authapi.login.authapi.service;

import com.authapi.login.authapi.dto.auth.AuthRequestDTO;
import com.authapi.login.authapi.dto.user.UserInfoDTO;

public interface AuthService {
	
    UserInfoDTO loginAndLog(AuthRequestDTO authRequestDTO);
}
