package com.github.juanv.authapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.juanv.authapi.model.LoginLog;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
	
}
