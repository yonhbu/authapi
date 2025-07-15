package com.authapi.login.authapi.service;

import com.authapi.login.authapi.dto.AuthRequestDTO;
import com.authapi.login.authapi.dto.UserInfoDTO;

public interface AuthService {
	
    UserInfoDTO loginAndLog(AuthRequestDTO req);
}
