package com.palmen.user.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import feign.RequestInterceptor;

@Configuration
public class FeignConfiguration {

	@Bean
	RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			requestTemplate.header(HttpHeaders.AUTHORIZATION, "Basic " + encodeCredentials("admin", "admin"));
		};
	}

	private String encodeCredentials(String username, String password) {
		String credentials = username + ":" + password;
		return java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
	}
}