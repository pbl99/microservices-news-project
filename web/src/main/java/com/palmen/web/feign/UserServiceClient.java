package com.palmen.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.palmen.web.models.LoginRequestDTO;
import com.palmen.web.models.UserRegistrationDTO;

@FeignClient(name = "msvc-user", url = "localhost:8090/api/user")
public interface UserServiceClient {

	@PostMapping("/register")
	ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userRegistrationDto);

	@PostMapping("/login")
	ResponseEntity<String> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO);

}
