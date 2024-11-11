package com.example.foodorderplatform.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
	@Bean
	public AuditorAware<String> auditorProvider(){
		// Optional.ofNullable() -> 로그인을 안한 상태에서는 SecurityContext가 null 일 수 있으므로 사용
		return () -> Optional.ofNullable(SecurityContextHolder.getContext())
				// SecurityContext에서 Authentication 객체를 가져오는 소스
				.map(SecurityContext::getAuthentication)
				// Authentication 객체에서 사용자의 이름 (username)을 가져오는 소스
				.map(Authentication::getName);
	}
}
