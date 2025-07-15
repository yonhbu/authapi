package com.authapi.login.authapi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.authapi.login.authapi.dto.AuthRequest;
import com.authapi.login.authapi.dto.AuthResponse;
import com.authapi.login.authapi.dto.UserInfo;
import com.authapi.login.authapi.feign.DummyAuthClient;
import com.authapi.login.authapi.model.LoginLog;
import com.authapi.login.authapi.repository.LoginLogRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final DummyAuthClient dummyAuthClient;
    private final LoginLogRepository loginLogRepository;

    public UserInfo loginAndLog(AuthRequest request) {
        // Paso 1: Autenticar con DummyJSON
        AuthResponse authResponse = dummyAuthClient.login(request);

        // Construye el header para /auth/me
        String bearer = "Bearer " + authResponse.getAccessToken();
        UserInfo userInfo = dummyAuthClient.getAuthenticatedUser(bearer);

        // Paso 3: Registrar el login en la base de datos
        LoginLog log = new LoginLog();
        log.setUsername(request.getUsername());
        log.setLoginTime(LocalDateTime.now());
        log.setAccessToken(authResponse.getAccessToken());
        log.setRefreshToken(""); // DummyJSON no devuelve refresh_token
        loginLogRepository.save(log);

        // Paso 4: Retornar la información del usuario autenticado
        return userInfo;
    }
}
