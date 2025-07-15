package com.github.juanv.authapi.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.juanv.authapi.dto.user.UserListResponseDTO;
import com.github.juanv.authapi.util.ApiConstants;



@FeignClient(name = "dummyAuthClient", url = ApiConstants.DUMMYJSON_BASE_URL)
public interface DummyUserClient {
	

    @GetMapping(ApiConstants.PATH_USERS)
    UserListResponseDTO getUsers(@RequestParam(defaultValue = "0") int skip,
                              @RequestParam(defaultValue = "30") int limit);      
        
}
