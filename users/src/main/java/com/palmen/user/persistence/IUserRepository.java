package com.palmen.user.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.palmen.user.entities.User;

public interface IUserRepository extends MongoRepository<User, Long> {
	User findByUsername(String name);
}
