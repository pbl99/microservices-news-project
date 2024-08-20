package com.palmen.news.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmen.news.entities.News;
import com.palmen.news.entities.persistence.INewsRepository;

@Service
public class NewsServiceImpl implements INewsService {

	@Autowired
	private INewsRepository newsRepository;

	@Override
	public void save(News news) {
		newsRepository.save(news);
	}

	@Override
	public List<News> findAll() {
		return newsRepository.findAll();
	}

}
