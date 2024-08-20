package com.palmen.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.palmen.news.entities.News;
import com.palmen.news.services.INewsService;

@RequestMapping("/api/news")
@RestController
public class NewsController {

	@Autowired
	private INewsService newsService;

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> saveNews(@RequestBody News news) {
		newsService.save(news);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/search/all")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(newsService.findAll());
	}

}
