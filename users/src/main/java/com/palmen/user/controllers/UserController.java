package com.palmen.user.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.palmen.user.entities.User;
import com.palmen.user.models.LoginRequestDTO;
import com.palmen.user.models.UserRegistrationDTO;
import com.palmen.user.services.IUserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create")
	public void saveUser(@RequestBody User user) {
		userService.Save(user);
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		return ResponseEntity.ok(userService.findById(id));
	}

	@GetMapping("/search/all")
	public ResponseEntity<?> findAllUser() {
		return ResponseEntity.ok(userService.findAll());
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userDto) {
		try {
			userService.register(userDto);
			return ResponseEntity.ok("User registered successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
		try {
			// Buscar al usuario en la base de datos
			Optional<User> userOptional = userService.findByUsername(loginRequestDTO.getUsername());

			if (userOptional.isPresent()) {
				User usuarioPresente = userOptional.get();

				// Verificar si la contraseña es correcta
				if (userService.checkPassword(usuarioPresente, loginRequestDTO.getPassword())) {
					// Configurar la autenticación en el contexto de seguridad
					Authentication authentication = authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
									loginRequestDTO.getPassword()));

					SecurityContextHolder.getContext().setAuthentication(authentication);

					// Aquí podrías generar un token JWT si fuera necesario
					return ResponseEntity.ok("Login successful");
				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred during authentication: " + e.getMessage());
		}
	}

}
