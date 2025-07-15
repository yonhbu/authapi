package com.authapi.login.authapi.feign.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.authapi.login.authapi.dto.auth.AuthRequestDTO;
import com.authapi.login.authapi.dto.auth.AuthResponseDTO;
import com.authapi.login.authapi.dto.user.UserInfoDTO;


@FeignClient(name = "dummyAuthClient", url = "https://dummyjson.com")
public interface DummyAuthClient {
	   

    @PostMapping("/auth/login")
    AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO);
    
    
    @GetMapping("/auth/me")
    UserInfoDTO getAuthenticatedUser(@RequestHeader("Cookie") String cookie);
    
        
}
