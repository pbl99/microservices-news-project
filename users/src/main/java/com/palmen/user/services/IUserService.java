package com.palmen.user.services;

import java.util.List;
import java.util.Optional;

import com.palmen.user.entities.User;
import com.palmen.user.models.Item;
import com.palmen.user.models.UserNewsDTO;

public interface IUserService {

	void Save(User user);

	Optional<User> findById(Long id);

	List<User> findAll();

	void addNewsToFavorites(Long userId, Long newsId);

	UserNewsDTO getUserWithFavoriteNews(Long userId);

	Item getNews(Long newsId);

	List<Item> findAllNews();

}
