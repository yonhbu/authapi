package com.github.juanv.authapi.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.juanv.authapi.dto.user.UserListResponseDTO;
import com.github.juanv.authapi.feign.user.DummyUserClient;
import com.github.juanv.authapi.util.ApiConstants;



@RestController
@RequestMapping(ApiConstants.API_USERS_BASE)
@RequiredArgsConstructor
public class UserController {

	private final DummyUserClient dummyUserClient; 

    @GetMapping
    public UserListResponseDTO list(
    		 @RequestParam(defaultValue = ApiConstants.DEFAULT_SKIP + "")  int skip,
             @RequestParam(defaultValue = ApiConstants.DEFAULT_LIMIT + "") int limit) {

        return dummyUserClient.getUsers(skip, limit);
    }
}