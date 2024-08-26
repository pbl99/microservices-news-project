package com.palmen.user.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private Long id;

	private String username;

	private String email;

	private String password;

	private List<Long> favoriteNewsIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Long> getFavoriteNewsIds() {
		return favoriteNewsIds;
	}

	public void setFavoriteNewsIds(List<Long> favoriteNewsIds) {
		this.favoriteNewsIds = favoriteNewsIds;
	}

}
