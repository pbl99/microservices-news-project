package com.palmen.news.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.palmen.news.entities.News;
import com.palmen.news.models.Item;
import com.palmen.news.models.NewsResponse;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<NewsResponse> getNewsById(@PathVariable("id") Long id){
		NewsResponse newsFind = newsService.findNewsById(id);
		return ResponseEntity.ok(newsFind);
	}

	@GetMapping("/latest")
	public ResponseEntity<NewsResponse> getLatestNews() {
		NewsResponse latestNews = newsService.getLatestNews();
		
		/* Estas eliminaciones se crean debido a que la API proporciona 3 noticias fijadas automáticamente las tres primeras 
		y no interesan en el propósito de la aplicación */
		latestNews.getPage().getItems().remove(2);
		latestNews.getPage().getItems().remove(1);
		latestNews.getPage().getItems().remove(0);

		return ResponseEntity.ok(latestNews);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<Item>> getNewsByCategory(@RequestParam String categoria){
		List<Item> categoryNews = newsService.getNewsByCategory(categoria);
		return ResponseEntity.ok(categoryNews);
	}

}
