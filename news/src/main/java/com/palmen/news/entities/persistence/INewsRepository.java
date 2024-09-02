package com.palmen.news.entities.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.palmen.news.entities.News;

@Repository
public interface INewsRepository extends MongoRepository<News, String>{

}
