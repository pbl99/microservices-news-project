package com.palmen.user.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.palmen.user.entities.User;
import com.palmen.user.feign.NewsClient;
import com.palmen.user.models.Item;
import com.palmen.user.models.NewsResponse;
import com.palmen.user.models.UserNewsDTO;
import com.palmen.user.persistence.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private NewsClient newsClient;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void Save(User user) {
		userRepository.save(user);
	}

	@Override
	public Item getNews(Long newsId) {
		ResponseEntity<NewsResponse> response = newsClient.getNewsById(newsId);
		NewsResponse newsResponse = response.getBody();
		if (newsResponse != null) {
			return newsResponse.getPage().getItems().stream().filter(item -> item.getId().equals(newsId.toString()))
					.findFirst().orElse(null);
		}
		return null;
	}

	@Override
	public List<Item> findAllNews() {
		ResponseEntity<NewsResponse> response = newsClient.findAll();
		NewsResponse newsResponse = response.getBody();
		if (newsResponse != null) {
			return newsResponse.getPage().getItems();
		}
		return Collections.emptyList();
	}

	@Override
	public void addNewsToFavorites(Long userId, Long newsId) {
		// Implementa la lógica para añadir noticias a favoritos
	}

	@Override
	public UserNewsDTO getUserWithFavoriteNews(Long userId) {
		// Implementa la lógica para obtener noticias favoritas del usuario
		return new UserNewsDTO();
	}
}
