package com.github.juanv.authapi.feign.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.github.juanv.authapi.dto.auth.AuthRequestDTO;
import com.github.juanv.authapi.dto.auth.AuthResponseDTO;
import com.github.juanv.authapi.dto.user.UserInfoDTO;
import com.github.juanv.authapi.util.ApiConstants;

@FeignClient(name = "dummyAuthClient", url = ApiConstants.DUMMYJSON_BASE_URL)
public interface DummyAuthClient {
	   

	@PostMapping(ApiConstants.PATH_AUTH_LOGIN)
    AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO);
    
    
	@GetMapping(ApiConstants.PATH_AUTH_ME)
    UserInfoDTO getAuthenticatedUser(@RequestHeader(ApiConstants.HEADER_COOKIE) String cookie);
    
        
}