package com.stackroute.newz.service;

import java.util.List;

import com.stackroute.newz.model.News;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotFoundException;

public interface NewsService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */
	
	public News addNews(News news) throws NewsAlreadyExistsException;

	public News getNewsById(int newsId) throws NewsNotFoundException;

	public News updateNews(News news) throws NewsNotFoundException;

	public void deleteNews(int newsId) throws NewsNotFoundException;
	
	public List<News> listAllNews();
	
	public List<News> listNewsByAuthor(String authorName);
	
	public List<News> listNewsByNewsSource(String newsSourceName);
	

}
