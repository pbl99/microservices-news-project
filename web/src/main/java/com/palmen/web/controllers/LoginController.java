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
import com.palmen.web.models.LoginRequestDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/web")
public class LoginController {

	@Autowired
	private UserServiceClient userServiceClient;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginRequestDTO", new LoginRequestDTO());
		return "login";
	}

	@PostMapping("/authenticate")
	public String login(@ModelAttribute("loginRequestDTO") LoginRequestDTO loginRequestDTO, Model model, HttpSession session) {
	    ResponseEntity<String> response = userServiceClient.authenticateUser(loginRequestDTO);

	    if (response.getStatusCode().is2xxSuccessful()) {
	        // Verifica el contenido de la respuesta
	        String responseBody = response.getBody();
	        System.out.println("Login response: " + responseBody);

	        // Guarda los detalles del usuario en la sesión
	        session.setAttribute("user", responseBody);
	        return "redirect:/news/latestNews";
	    } else {
	        // Verifica el código de estado de la respuesta
	        System.out.println("Error response status: " + response.getStatusCode());
	        model.addAttribute("error", "Credenciales inválidas");
	        return "login";
	    }
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
