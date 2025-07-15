package com.github.juanv.authapi.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.github.juanv.authapi.dto.auth.AuthRequestDTO;
import com.github.juanv.authapi.dto.auth.AuthResponseDTO;
import com.github.juanv.authapi.dto.user.UserInfoDTO;
import com.github.juanv.authapi.feign.auth.DummyAuthClient;
import com.github.juanv.authapi.model.LoginLog;
import com.github.juanv.authapi.repository.LoginLogRepository;
import com.github.juanv.authapi.util.ApiConstants;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DummyAuthClient   dummyAuthClient;
    private final LoginLogRepository loginLogRepository;

    @Override
    public UserInfoDTO loginAndLog(AuthRequestDTO authRequestDTO) {

        /* 1️⃣  Login externo */
        AuthResponseDTO auth = dummyAuthClient.login(authRequestDTO);

        /* 2️⃣  Construir la cookie */
        String cookieHeader = ApiConstants.COOKIE_ACCESS_TOKEN  + "=" + auth.getAccessToken()
                            + "; " + ApiConstants.COOKIE_REFRESH_TOKEN + "="
                            + Optional.ofNullable(auth.getRefreshToken()).orElse("");

        /* 3️⃣  Obtener el perfil autenticado */
        UserInfoDTO me = dummyAuthClient.getAuthenticatedUser(cookieHeader);

        /* 4️⃣  Registrar en la base de datos */
        LoginLog log = new LoginLog();
        log.setUsername(authRequestDTO.getUsername());
        log.setLoginTime(LocalDateTime.now());
        log.setAccessToken(auth.getAccessToken());
        log.setRefreshToken(auth.getRefreshToken());
        loginLogRepository.save(log);

        /* 5️⃣  Devolver el perfil */
        return me;
    }
}
