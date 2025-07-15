package com.authapi.login.authapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.authapi.login.authapi.dto.AuthRequest;
import com.authapi.login.authapi.dto.AuthResponse;
import com.authapi.login.authapi.dto.UserInfo;

@FeignClient(name = "dummyAuthClient", url = "https://dummyjson.com")
public interface DummyAuthClient {

    @PostMapping("/auth/login")
    AuthResponse login(@RequestBody AuthRequest request);

    @GetMapping("/auth/me")
    UserInfo getAuthenticatedUser(@RequestHeader("Authorization") String bearerToken);
}
