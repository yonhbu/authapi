package com.authapi.login.authapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.authapi.login.authapi.dto.AuthRequestDTO;
import com.authapi.login.authapi.dto.AuthResponseDTO;
import com.authapi.login.authapi.dto.UserInfoDTO;

@FeignClient(name = "dummyAuthClient", url = "https://dummyjson.com")
public interface DummyAuthClient {

    @PostMapping("/auth/login")
    AuthResponseDTO login(@RequestBody AuthRequestDTO request);

    @GetMapping("/auth/me")
    UserInfoDTO getAuthenticatedUser(@RequestHeader("Cookie") String cookie);
    
}
