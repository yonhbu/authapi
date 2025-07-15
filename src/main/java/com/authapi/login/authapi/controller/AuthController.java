package com.authapi.login.authapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.authapi.login.authapi.dto.AuthRequest;
import com.authapi.login.authapi.dto.UserInfo;
import com.authapi.login.authapi.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserInfo> login(@RequestBody AuthRequest request) {
        UserInfo user = authService.loginAndLog(request);
        return ResponseEntity.ok(user);
    }
}
