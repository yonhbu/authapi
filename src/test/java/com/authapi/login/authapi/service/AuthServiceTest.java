package com.authapi.login.authapi.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.authapi.login.authapi.dto.AuthRequest;
import com.authapi.login.authapi.dto.AuthResponse;
import com.authapi.login.authapi.dto.UserInfo;
import com.authapi.login.authapi.feign.DummyAuthClient;
import com.authapi.login.authapi.model.LoginLog;
import com.authapi.login.authapi.repository.LoginLogRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Test
    void loginAndLog_debeGuardarYRetornarUsuario() {
        // Mock de dependencias
        DummyAuthClient dummyAuthClient = mock(DummyAuthClient.class);
        LoginLogRepository loginLogRepository = mock(LoginLogRepository.class);

        AuthService authService = new AuthService(dummyAuthClient, loginLogRepository);

        // Datos simulados
        AuthRequest request = new AuthRequest();
        request.setUsername("emilys");
        request.setPassword("emilyspass");

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("fakeToken");
        authResponse.setUsername("emilys");

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("emilys");
        userInfo.setEmail("emily@example.com");

        // Comportamiento esperado de mocks
        when(dummyAuthClient.login(any())).thenReturn(authResponse);
        when(dummyAuthClient.getAuthenticatedUser(any())).thenReturn(userInfo);

        // Ejecutar
        UserInfo result = authService.loginAndLog(request);

        // Verificar
        assertEquals("emilys", result.getUsername());
        verify(loginLogRepository, times(1)).save(any(LoginLog.class));
        verify(dummyAuthClient, times(1)).login(any(AuthRequest.class));
        verify(dummyAuthClient, times(1)).getAuthenticatedUser(anyString());
    }
}
