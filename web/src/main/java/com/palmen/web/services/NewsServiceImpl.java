package com.palmen.web.services;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.palmen.web.feign.NewsClient;
import com.palmen.web.models.Item;
import com.palmen.web.models.NewsResponse;

@Service
public class NewsServiceImpl implements INewsService {

	@Autowired
	private NewsClient newsClient;

	@Override
	public List<Item> getAllNews() {
		ResponseEntity<NewsResponse> response = newsClient.findAll();
		NewsResponse newsResponse = response.getBody();
		if (newsResponse != null) {
			return newsResponse.getPage().getItems();
		}
		return Collections.emptyList();
	}

	@Override
	public List<Item> getNewsByCategory(String categoria) {
		ResponseEntity<List<Item>> response = newsClient.getNewsByCategory(categoria);
		if (response != null) {
			return response.getBody();
		}
		return Collections.emptyList();
	}

	@Override
	public NewsResponse getNewsById(Long id) {
		ResponseEntity<NewsResponse> response = newsClient.getNewsById(id);

		if (response != null && response.getBody() != null) {
			return response.getBody();
		} else {
			throw new NoSuchElementException("News with ID " + id + " not found");
		}
	}
}
