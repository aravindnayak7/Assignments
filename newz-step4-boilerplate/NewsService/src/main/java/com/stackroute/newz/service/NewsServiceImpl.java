package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotFoundException;

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
	
	/*
	 * Autowiring should be implemented for the NewsSourceRepository.
	 */
	
	
	/*
	 * Add a new news. Throw NewsAlreadyExistsException if the news with specified
	 * newsId already exists.
	 */
	@Autowired
	private NewsRepository newsrepository;
	@Autowired
	private NewsSourceRepository newssourcerepository;
	@Override
	public News addNews(News news) throws NewsAlreadyExistsException {
		Optional<News> newsobj=this.newsrepository.findById(news.getNewsId());
		News addnews=null;
		if(newsobj.isPresent()) {
			throw new NewsAlreadyExistsException();
		}else {
			addnews=this.newsrepository.save(news);
		}
		return addnews;
		
	}

	/*
	 * Update an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	@Override
	public News updateNews(News news) throws NewsNotFoundException {
		Optional<News> newsobj=this.newsrepository.findById(news.getNewsId());
		News unews=null;
		if(newsobj.isPresent()) {
			unews=newsobj.get();
			unews.setAuthor(news.getAuthor());
			unews.setContent(news.getContent());
			unews.setDescription(news.getDescription());
			unews.setNewsId(news.getNewsId());
			unews.setNewsSource(news.getNewsSource());
			unews.setPublishedAt(news.getPublishedAt());
			unews.setReminder(news.getReminder());
			unews.setTitle(news.getTitle());
			unews.setUrl(news.getUrl());
			unews.setUrlToImage(news.getUrlToImage());
			unews=this.newsrepository.save(news);
		}else {
			throw new NewsNotFoundException();
		}
		return unews;
	}
	
	/*
	 * Delete an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	@Override
	public void deleteNews(int newsId) throws NewsNotFoundException {
		Optional<News> newsobj=this.newsrepository.findById(newsId);
		if(newsobj.isPresent()) {
			newsrepository.deleteById(newsobj.get().getNewsId());
		}else {
			throw new NewsNotFoundException();
		}
		
	}

	/*
	 * Retrieve all existing news
	 */
	@Override
	public List<News> listAllNews() {
		
		return this.newsrepository.findAll();
	}

	/*
	 * Retrieve an existing news by it's newsId. Throw NewsNotFoundException if the 
	 * news with specified newsId does not exist.
	 */
	@Override
	public News getNewsById(int newsId) throws NewsNotFoundException {
		Optional<News> newobj=this.newsrepository.findById(newsId);
		News n=null;
		if(newobj.isPresent()) {
			n=newobj.get();
		}else {
			throw new NewsNotFoundException();
		}
		return n;
	}

	/*
	 * Retrieve an existing news by it's authorName.
	 */
	@Override
	public List<News> listNewsByAuthor(String authorName) {
		List<News> auhtNews = this.newsrepository.findByAuthor(authorName);
		return auhtNews;
	}

	/*
	 * Retrieve an existing news by it's sourceName.
	 */
	@Override
	public List<News> listNewsByNewsSource(String sourceName) {
		NewsSource n=newssourcerepository.findBySourceNameIgnoreCase(sourceName);
		List<News> sourcnameNews = this.newsrepository.findByNewsSource(n);
		return sourcnameNews;
	}

}
