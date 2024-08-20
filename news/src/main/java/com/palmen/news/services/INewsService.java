package com.palmen.news.services;

import java.util.List;

import com.palmen.news.entities.News;

public interface INewsService {

	void save(News news);

	List<News> findAll();

}
