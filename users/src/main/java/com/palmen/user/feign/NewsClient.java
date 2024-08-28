package com.palmen.user.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.palmen.user.models.NewsResponse;

@FeignClient(name = "msvc-news", url = "localhost:9090/api/news", configuration = FeignConfiguration.class)
public interface NewsClient {

	@GetMapping("/{id}")
    ResponseEntity<NewsResponse> getNewsById(@PathVariable("id") Long id);
	
	@GetMapping("/latest")
	ResponseEntity<NewsResponse> findAll();
}
