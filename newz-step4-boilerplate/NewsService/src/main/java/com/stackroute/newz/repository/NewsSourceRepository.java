package com.stackroute.newz.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.NewsSource;

/*
* This class is implementing the MongoRepository interface for NewsSource.
* Annotate this class with @Repository annotation
* */
@Repository
public interface NewsSourceRepository extends MongoRepository<NewsSource,String> {

	public NewsSource findBySourceNameIgnoreCase(String sourceName);
	
}
