package com.authapi.login.authapi.service;


import com.authapi.login.authapi.dto.auth.AuthRequestDTO;
import com.authapi.login.authapi.dto.auth.AuthResponseDTO;
import com.authapi.login.authapi.dto.user.UserInfoDTO;
import com.authapi.login.authapi.feign.auth.DummyAuthClient;
import com.authapi.login.authapi.model.LoginLog;
import com.authapi.login.authapi.repository.LoginLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DummyAuthClient dummyAuthClient;
    private final LoginLogRepository loginLogRepository;

    @Override
    public UserInfoDTO loginAndLog(AuthRequestDTO authRequestDTO) {
    	
        AuthResponseDTO authResponseDTO = dummyAuthClient.login(authRequestDTO);

        String cookieHeader = "accessToken=" + authResponseDTO.getAccessToken();
        UserInfoDTO me = dummyAuthClient.getAuthenticatedUser(cookieHeader);

        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(authRequestDTO.getUsername());
        loginLog.setLoginTime(LocalDateTime.now());
        loginLog.setAccessToken(authResponseDTO.getAccessToken());
        loginLog.setRefreshToken(authResponseDTO.getRefreshToken());
        loginLogRepository.save(loginLog);

        return me;
    }
}


