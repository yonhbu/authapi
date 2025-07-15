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

   
        AuthResponseDTO auth = dummyAuthClient.login(authRequestDTO);
       
        String cookieHeader = buildCookieHeader(auth.getAccessToken(), auth.getRefreshToken());
        UserInfoDTO me = dummyAuthClient.getAuthenticatedUser(cookieHeader);
       
        LoginLog loginLog = buildLoginLog(authRequestDTO, auth, cookieHeader);
        loginLog.setCookies(cookieHeader);
        loginLogRepository.save(loginLog);
            

        return me;
        
    }
    
    
    private String buildCookieHeader(String accessToken, String refreshToken) {
        return ApiConstants.COOKIE_ACCESS_TOKEN + "=" + accessToken + "; "
             + ApiConstants.COOKIE_REFRESH_TOKEN + "="
             + Optional.ofNullable(refreshToken).orElse("");
    }
    
    
    private LoginLog buildLoginLog(AuthRequestDTO req, AuthResponseDTO auth, String cookies) {
        return LoginLog.builder()
                .username(req.getUsername())
                .loginTime(LocalDateTime.now())
                .accessToken(auth.getAccessToken())
                .refreshToken(auth.getRefreshToken())
                .cookies(cookies)
                .build();
    }


}
