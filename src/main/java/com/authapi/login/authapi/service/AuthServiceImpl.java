package com.authapi.login.authapi.service;


import com.authapi.login.authapi.dto.AuthRequestDTO;
import com.authapi.login.authapi.dto.AuthResponseDTO;
import com.authapi.login.authapi.dto.UserInfoDTO;
import com.authapi.login.authapi.feign.DummyAuthClient;
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
    public UserInfoDTO loginAndLog(AuthRequestDTO req) {
    	
        AuthResponseDTO auth = dummyAuthClient.login(req);

        String cookieHeader = "accessToken=" + auth.getAccessToken();
        UserInfoDTO me = dummyAuthClient.getAuthenticatedUser(cookieHeader);

        LoginLog log = new LoginLog();
        log.setUsername(req.getUsername());
        log.setLoginTime(LocalDateTime.now());
        log.setAccessToken(auth.getAccessToken());
        log.setRefreshToken(auth.getRefreshToken());
        loginLogRepository.save(log);

        return me;
    }
}


