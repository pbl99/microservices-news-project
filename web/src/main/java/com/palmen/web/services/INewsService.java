package com.palmen.web.services;

import java.util.List;
import com.palmen.web.models.Item;
import com.palmen.web.models.NewsResponse;

public interface INewsService {

	List<Item> getAllNews();

	List<Item> getNewsByCategory(String categoria);

	NewsResponse getNewsById(Long id);
}
