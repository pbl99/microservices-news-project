package com.palmen.user.persistence;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.palmen.user.entities.User;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
}
