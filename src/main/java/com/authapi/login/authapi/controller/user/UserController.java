package com.authapi.login.authapi.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.authapi.login.authapi.dto.user.UserListResponseDTO;
import com.authapi.login.authapi.feign.user.DummyUserClient;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final DummyUserClient dummyUserClient; 

    @GetMapping
    public UserListResponseDTO list(
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "30") int limit) {

        return dummyUserClient.getUsers(skip, limit);
    }
}