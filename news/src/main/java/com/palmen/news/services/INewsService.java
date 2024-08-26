package com.palmen.news.services;

import java.util.List;

import com.palmen.news.entities.News;
import com.palmen.news.models.Item;
import com.palmen.news.models.NewsResponse;

public interface INewsService {

	void save(News news);

	List<News> findAll();

	NewsResponse getLatestNews();

	List<Item> getNewsByCategory(String mainCategory);

	NewsResponse findNewsById(Long id);

}
