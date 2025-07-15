package com.authapi.login.authapi.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.authapi.login.authapi.dto.AuthRequestDTO;
import com.authapi.login.authapi.dto.AuthResponseDTO;
import com.authapi.login.authapi.dto.UserInfoDTO;
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

       // AuthService authService = new AuthService(dummyAuthClient, loginLogRepository);

        // Datos simulados
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("emilys");
        request.setPassword("emilyspass");

        AuthResponseDTO authResponse = new AuthResponseDTO();
        authResponse.setAccessToken("fakeToken");
        authResponse.setUsername("emilys");

        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUsername("emilys");
        userInfo.setEmail("emily@example.com");

        // Comportamiento esperado de mocks
        when(dummyAuthClient.login(any())).thenReturn(authResponse);
        when(dummyAuthClient.getAuthenticatedUser(any())).thenReturn(userInfo);

        // Ejecutar
      //  UserInfo result = authService.loginAndLog(request);

        // Verificar
      //  assertEquals("emilys", result.getUsername());
        verify(loginLogRepository, times(1)).save(any(LoginLog.class));
        verify(dummyAuthClient, times(1)).login(any(AuthRequestDTO.class));
        verify(dummyAuthClient, times(1)).getAuthenticatedUser(anyString());
    }
}
