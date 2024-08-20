package com.palmen.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.palmen.user.entities.User;
import com.palmen.user.services.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create")
	public void saveUser(@RequestBody User user) {
		userService.Save(user);
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findById(id));
	}

	@GetMapping("/search/all")
	public ResponseEntity<?> findAllUser() {
		return ResponseEntity.ok(userService.findAll());
	}

}
