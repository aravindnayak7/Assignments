package com.stackroute.newz.service;

import java.util.List;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.util.exception.NewsSourceAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

public interface NewsSourceService {

	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */
	public NewsSource addNewsSource(NewsSource newsSource) throws NewsSourceAlreadyExistsException;

	public void deleteNewsSource(int sourceId) throws NewsSourceNotFoundException;

	public NewsSource updateNewsSource(NewsSource newsSource, int sourceId) throws NewsSourceNotFoundException;

	public NewsSource getNewsSourceById(int sourceId) throws NewsSourceNotFoundException;
	
	public List<NewsSource> listAllNewsSources();

}
