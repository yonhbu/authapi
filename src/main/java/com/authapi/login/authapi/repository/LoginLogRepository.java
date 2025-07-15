package com.authapi.login.authapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.authapi.login.authapi.model.LoginLog;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
	
}
