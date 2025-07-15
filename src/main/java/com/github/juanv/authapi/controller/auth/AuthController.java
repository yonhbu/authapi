package com.github.juanv.authapi.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.juanv.authapi.dto.auth.AuthRequestDTO;
import com.github.juanv.authapi.dto.user.UserInfoDTO;
import com.github.juanv.authapi.service.AuthService;
import com.github.juanv.authapi.util.ApiConstants;



@RestController
@RequestMapping(ApiConstants.API_AUTH_BASE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiConstants.PATH_LOGIN )
    public ResponseEntity<UserInfoDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
    	
        UserInfoDTO userInfoDTO = authService.loginAndLog(authRequestDTO);
        return ResponseEntity.ok(userInfoDTO);
    }
}
