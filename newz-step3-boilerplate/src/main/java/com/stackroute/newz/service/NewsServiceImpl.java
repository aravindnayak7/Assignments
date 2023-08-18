package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotExistsException;


/*
 * This class is implementing the NewsService interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */
@Service
public class NewsServiceImpl implements NewsService {

	/*
	 * Autowiring should be implemented for the NewsRepository.
	 */
	@Autowired
	private NewsRepository newsrepo;

	/*
	 * Add a new news. Throw NewsAlreadyExistsException if the news with specified
	 * newsId already exists.
	 */
	public News addNews(News news) throws NewsAlreadyExistsException {
		News n = newsrepo.getOne(news.getNewsId());
		News addedNews=null;
		if(n!=null) {
			throw new NewsAlreadyExistsException();
		}
		else {
			addedNews = newsrepo.save(news);
			
		}
		return addedNews;
	}

	/*
	 * Retrieve an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public News getNews(int newsId) throws NewsNotExistsException {
		Optional<News> n = newsrepo.findById(newsId);
		if(n.isPresent()) {
			News news = n.get();
			return news;
		}else {
			throw new NewsNotExistsException();
		}
	}

	/*
	 * Retrieve all existing news
	 */
	public List<News> getAllNews() {

		return newsrepo.findAll();
	}

	
	/*
	 * Update an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public News updateNews(News news) throws NewsNotExistsException {
		
		News n = newsrepo.getOne(news.getNewsId());
		News updNews=null;
		if(n!=null) {
			
			n.setTitle(news.getTitle());
			n.setAuthor(news.getAuthor());
			n.setContent(news.getContent());
			n.setDescription(news.getDescription());
			n.setPublishedAt(news.getPublishedAt());
			n.setUrl(news.getUrl());
			n.setUrlToImage(news.getUrlToImage());
			
			updNews=newsrepo.saveAndFlush(n);
		}else {
			throw new NewsNotExistsException();
		}
		return updNews;
	}

	/*
	 * Delete an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public void deleteNews(int newsId) throws NewsNotExistsException {
News n = newsrepo.getOne(newsId);
		
		if(n!=null) {
			newsrepo.deleteById(newsId);
		
		}else {
			throw new NewsNotExistsException();
			
		}

	}

}
