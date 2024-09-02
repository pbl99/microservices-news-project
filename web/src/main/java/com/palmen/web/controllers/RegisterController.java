package com.palmen.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.palmen.web.feign.UserServiceClient;
import com.palmen.web.models.UserRegistrationDTO;

@Controller
@RequestMapping("/api/web")
public class RegisterController {

	@Autowired
	private UserServiceClient userServiceClient;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("userDto", new UserRegistrationDTO());
		return "register";
	}

	@PostMapping("/registrar")
	public String registerUser(@ModelAttribute("userDto") UserRegistrationDTO userDto, Model model) {
		// Llama al microservicio de usuario para registrar al nuevo usuario
		ResponseEntity<String> response = userServiceClient.registerUser(userDto);

		if (response.getStatusCode().is2xxSuccessful()) {
			model.addAttribute("message", "Usuario registrado con éxito!");
			return "registrationSuccess";
		} else {
			model.addAttribute("error", "Error en el registro del usuario. Por favor, inténtalo de nuevo.");
			return "register";
		}
	}
}
