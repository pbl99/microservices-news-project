package com.palmen.user.services;

import java.util.List;
import java.util.Optional;

import com.palmen.user.entities.User;
import com.palmen.user.models.Item;
import com.palmen.user.models.UserNewsDTO;
import com.palmen.user.models.UserRegistrationDTO;

public interface IUserService {

	void Save(User user);

	Optional<User> findById(String id);

	List<User> findAll();

	void addNewsToFavorites(Long userId, Long newsId);

	UserNewsDTO getUserWithFavoriteNews(Long userId);

	Item getNews(Long newsId);

	List<Item> findAllNews();

	void register(UserRegistrationDTO userDto);

	Optional<User> findByUsername(String username);

	boolean checkPassword(User user, String rawPassword);

}
