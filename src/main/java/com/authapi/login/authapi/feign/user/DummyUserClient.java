package com.authapi.login.authapi.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import com.authapi.login.authapi.dto.user.UserInfoDTO;
import com.authapi.login.authapi.dto.user.UserListResponseDTO;



@FeignClient(name = "dummyAuthClient", url = "https://dummyjson.com")
public interface DummyUserClient {
	

    @GetMapping("/users")
    UserListResponseDTO getUsers(@RequestParam(defaultValue = "0") int skip,
                              @RequestParam(defaultValue = "30") int limit);      
        
}
