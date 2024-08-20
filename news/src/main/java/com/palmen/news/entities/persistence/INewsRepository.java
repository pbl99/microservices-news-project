package com.palmen.news.entities.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.palmen.news.entities.News;

public interface INewsRepository extends MongoRepository<News, String>{

}
