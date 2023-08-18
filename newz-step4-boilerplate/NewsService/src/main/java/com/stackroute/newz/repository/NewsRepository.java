package com.stackroute.newz.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.NewsSource;

/*
* This class is implementing the MongoRepository interface for News.
* Annotate this class with @Repository annotation
* */
@Repository
public interface NewsRepository extends MongoRepository<News,Integer> {

	public List<News> findByAuthor(String authorName);
	
	public List<News> findByNewsSource(NewsSource newsSource);

	
}
