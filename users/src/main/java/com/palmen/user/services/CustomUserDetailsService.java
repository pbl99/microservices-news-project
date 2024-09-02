package com.palmen.user.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.palmen.user.entities.User;
import com.palmen.user.persistence.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final IUserRepository userRepository;

	public CustomUserDetailsService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new CustomUserDetails(user); // CustomUserDetails implementa UserDetails
	}

}
