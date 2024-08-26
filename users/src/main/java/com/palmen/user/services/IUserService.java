package com.palmen.user.services;

import java.util.List;
import java.util.Optional;

import com.palmen.user.entities.User;

public interface IUserService {

	void Save(User user);

	Optional<User> findById(Long id);

	List<User> findAll();

}
