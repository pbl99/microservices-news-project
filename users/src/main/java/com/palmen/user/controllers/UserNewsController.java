package com.palmen.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmen.user.models.Item;
import com.palmen.user.models.UserNewsDTO;
import com.palmen.user.services.IUserService;

@RestController
@RequestMapping("/api/usernews")
public class UserNewsController {

	@Autowired
	private IUserService userService;

	@GetMapping("{newsId}")
	public ResponseEntity<Item> getNews(@PathVariable Long newsId) {
		Item item = userService.getNews(newsId);
		return ResponseEntity.ok(item);
	}

	@PostMapping("/{userId}/favorites")
	public ResponseEntity<Void> addNewsToFavorites(@PathVariable Long userId, @RequestBody Long newsId) {
		userService.addNewsToFavorites(userId, newsId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{userId}/favorites")
	public ResponseEntity<UserNewsDTO> getUserWithFavoriteNews(@PathVariable Long userId) {
		UserNewsDTO userNewsDTO = userService.getUserWithFavoriteNews(userId);
		return ResponseEntity.ok(userNewsDTO);
	}

	@GetMapping("/allnews")
	public ResponseEntity<List<Item>> getAllNews() {
		List<Item> news = userService.findAllNews();
		return ResponseEntity.ok(news);
	}
}
