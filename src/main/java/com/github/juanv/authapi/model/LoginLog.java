package com.github.juanv.authapi.model;

import com.github.juanv.authapi.util.ApiConstants;

import lombok.Data;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Data
@Entity
@Table(name = ApiConstants.TABLE_NAME)
public class LoginLog {
  
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ApiConstants.COL_ID)
    private Long id;

    @Column(name = ApiConstants.COL_USERNAME)
    private String username;

    @Column(name = ApiConstants.COL_LOGIN_TIME)
    private LocalDateTime loginTime;

    @Column(name = ApiConstants.COL_ACCESS_TOKEN, columnDefinition = "TEXT")
    private String accessToken;

    @Column(name = ApiConstants.COL_REFRESH_TOKEN, columnDefinition = "TEXT")
    private String refreshToken;
    
    
}


