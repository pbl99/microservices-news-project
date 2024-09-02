package com.palmen.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.requestMatchers("/css/**", "/api/web/**", "/register", "/registrar", "/authenticate")
						.permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/api/web/login").permitAll().defaultSuccessUrl("/api/web/latestNews", true)
						.failureUrl("/login?error=true"))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout=true")

				);
		return http.build();
	}
}
