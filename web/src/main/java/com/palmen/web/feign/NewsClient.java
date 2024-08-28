package com.palmen.web.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.palmen.web.models.Item;
import com.palmen.web.models.NewsResponse;

@FeignClient(name = "msvc-news", url = "localhost:9090/api/news")
public interface NewsClient {
	
	@GetMapping("/{id}")
	ResponseEntity<NewsResponse> getNewsById(@PathVariable("id") Long id);

	@GetMapping("/latest")
	ResponseEntity<NewsResponse> findAll();
	
	@GetMapping("/categories")
	ResponseEntity<List<Item>> getNewsByCategory(@RequestParam String categoria);
	
}
