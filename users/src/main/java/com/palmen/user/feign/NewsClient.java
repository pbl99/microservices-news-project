package com.palmen.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.palmen.user.models.UserNewsDTO;

@FeignClient(name = "news-service")
public interface NewsClient {

	@GetMapping("/api/news/{id}")
	UserNewsDTO getNewsById(@PathVariable("id") Long id);
}
