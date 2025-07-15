package com.authapi.login.authapi.dto;

import lombok.Data;

@Data
public class UserInfo {
    private int id;
    private String username;
    private String email;
}
