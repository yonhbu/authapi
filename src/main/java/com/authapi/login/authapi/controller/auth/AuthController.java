package com.authapi.login.authapi.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authapi.login.authapi.dto.auth.AuthRequestDTO;
import com.authapi.login.authapi.dto.user.UserInfoDTO;
import com.authapi.login.authapi.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserInfoDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
    	
        UserInfoDTO userInfoDTO = authService.loginAndLog(authRequestDTO);
        return ResponseEntity.ok(userInfoDTO);
    }
}
