package com.palmen.user.models;

import java.util.List;

public class UserNewsDTO {
	private Long userId;
	private String username;
	private String email;
	private List<NewsResponse> favoriteNews;

	// Getters y setters

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<NewsResponse> getFavoriteNews() {
		return favoriteNews;
	}

	public void setFavoriteNews(List<NewsResponse> favoriteNews) {
		this.favoriteNews = favoriteNews;
	}
}
