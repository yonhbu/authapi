package com.github.juanv.authapi.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import com.github.juanv.authapi.dto.auth.AuthRequestDTO;
import com.github.juanv.authapi.dto.auth.AuthResponseDTO;
import com.github.juanv.authapi.dto.user.UserInfoDTO;
import com.github.juanv.authapi.feign.auth.DummyAuthClient;
import com.github.juanv.authapi.model.LoginLog;
import com.github.juanv.authapi.repository.LoginLogRepository;
import com.github.juanv.authapi.util.ApiConstants;

import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock DummyAuthClient dummyAuthClient;
    @Mock LoginLogRepository loginLogRepository;

    @InjectMocks AuthServiceImpl authService;

    
    @Test
    void loginAndLog_successful() {
        // Arrange
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("emilys");
        request.setPassword("emilyspass");

        AuthResponseDTO authResp = new AuthResponseDTO();
        authResp.setUsername("emilys");
        authResp.setAccessToken("jwt123");
        authResp.setRefreshToken("ref123");

        UserInfoDTO me = new UserInfoDTO();
        me.setUsername("emilys");
        me.setEmail("emily@mail.com");

        when(dummyAuthClient.login(any())).thenReturn(authResp);
        when(dummyAuthClient.getAuthenticatedUser(anyString())).thenReturn(me);

      
        UserInfoDTO result = authService.loginAndLog(request);

        
        assertThat(result.getUsername()).isEqualTo("emilys");

       
        ArgumentCaptor<LoginLog> cap = ArgumentCaptor.forClass(LoginLog.class);
        verify(loginLogRepository).save(cap.capture());
        LoginLog saved = cap.getValue();
        assertThat(saved.getUsername()).isEqualTo("emilys");
        assertThat(saved.getAccessToken()).isEqualTo("jwt123");
        assertThat(saved.getRefreshToken()).isEqualTo("ref123");

    
        ArgumentCaptor<String> cookieCap = ArgumentCaptor.forClass(String.class);
        verify(dummyAuthClient).getAuthenticatedUser(cookieCap.capture());
        assertThat(cookieCap.getValue())
            .isEqualTo(ApiConstants.COOKIE_ACCESS_TOKEN + "=jwt123; "
                     + ApiConstants.COOKIE_REFRESH_TOKEN + "=ref123");
    }


    @Test
    void loginAndLog_invalidCredentialsThrows() {
       
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("baduser");
        request.setPassword("wrong");

       
        Request fakeReq = Request.create(Request.HttpMethod.POST, "/auth/login",
                             java.util.Collections.emptyMap(), null, new RequestTemplate());
        FeignException unauthorized =
                FeignException.errorStatus("login",
                        feign.Response.builder()
                                      .status(401).reason("Unauthorized")
                                      .request(fakeReq).build());

        when(dummyAuthClient.login(any())).thenThrow(unauthorized);

        
        assertThatThrownBy(() -> authService.loginAndLog(request))
            .isInstanceOf(FeignException.Unauthorized.class);
        verify(loginLogRepository, never()).save(any());
    }


    @Test
    void loginAndLog_refreshTokenNull_cookieStillValid() {
        
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("michaelw");
        request.setPassword("michaelspass");

        AuthResponseDTO authResp = new AuthResponseDTO();
        authResp.setUsername("michaelw");
        authResp.setAccessToken("jwt456");
        authResp.setRefreshToken(null);                 
        UserInfoDTO me = new UserInfoDTO();
        me.setUsername("michaelw");

        when(dummyAuthClient.login(any())).thenReturn(authResp);
        when(dummyAuthClient.getAuthenticatedUser(anyString())).thenReturn(me);

       
        authService.loginAndLog(request);

      
        ArgumentCaptor<String> cookieCap = ArgumentCaptor.forClass(String.class);
        verify(dummyAuthClient).getAuthenticatedUser(cookieCap.capture());
        assertThat(cookieCap.getValue())
            .isEqualTo(ApiConstants.COOKIE_ACCESS_TOKEN + "=jwt456; "
                     + ApiConstants.COOKIE_REFRESH_TOKEN + "=");
    }
}
