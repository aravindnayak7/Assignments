package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.util.exception.NewsSourceAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

/*
 * This class is implementing the NewsService interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */
@Service
public class NewsSourceServiceImpl implements NewsSourceService {
	
	/*
	 * Autowiring should be implemented for the NewsSourceRepository.
	 */
	

	/*
	 * Add a new newsSource. Throw NewsSourceAlreadyExistsException if the news source with specified
	 * sourceId already exists.
	 */
	@Autowired
	private NewsSourceRepository newsRepository;
	public NewsSource addNewsSource(NewsSource newsSource) throws NewsSourceAlreadyExistsException {
		Optional<NewsSource> news=this.newsRepository.findById(newsSource.getSourceId());
		NewsSource latestNews=null;
		if(news.isPresent())
		{
			System.out.println("News already exists");
			throw new NewsSourceAlreadyExistsException();
		}else {
			latestNews=this.newsRepository.save(newsSource);
		}
		return latestNews;
	}

	
	/*
	 * Delete an existing news source by it's sourceId. Throw NewsSourceNotFoundException if the 
	 * news source with specified sourceId does not exist.
	 */
	public void deleteNewsSource(int sourceId) throws NewsSourceNotFoundException {
		Optional<NewsSource> news=this.newsRepository.findById(sourceId);
		NewsSource delNews=null;
		if(news.isPresent()) {
			delNews=news.get();
			newsRepository.deleteById(delNews.getSourceId());
		}else {
			throw new NewsSourceNotFoundException();
		}
		
		
		
	}

	/*
	 * Update an existing news source by it's sourceId. Throw NewsSourceNotFoundException if the 
	 * news source with specified sourceId does not exist.
	 */
	public NewsSource updateNewsSource(NewsSource newsSource, int sourceId) throws NewsSourceNotFoundException {
		Optional<NewsSource> updateNews=this.newsRepository.findById(sourceId);
		NewsSource unews=null;
		NewsSource eobj=null;
		if(updateNews.isPresent()) {
			unews=updateNews.get();
					unews.setSourceName(eobj.getSourceName());
			unews.setSourceId(eobj.getSourceId());
			unews.setSourceDesc(eobj.getSourceDesc());
			unews.setSourceCreatedBy(eobj.getSourceCreatedBy());
			unews.setSourceCreatedBy(eobj.getSourceCreatedBy());
			unews=this.newsRepository.save(eobj);
		}else {
			throw new NewsSourceNotFoundException(); 
		}
		return unews;
	}

	/*
	 * Retrieve an existing news source by it's sourceId. Throw NewsSourceNotFoundException if the 
	 * news source with specified sourceId does not exist.
	 */
	public NewsSource getNewsSourceById(int sourceId) throws NewsSourceNotFoundException {
		Optional<NewsSource> news=this.newsRepository.findById(sourceId);
		NewsSource newsDisplay=null;
		if(news.isPresent()) {
			newsDisplay=news.get();
		}else {
			throw new NewsSourceNotFoundException();
		}
		return newsDisplay;
	}

	/*
	 * Retrieve all existing news source
	 */
	public List<NewsSource> listAllNewsSources() {
		
		return this.newsRepository.findAll();
	}

}
