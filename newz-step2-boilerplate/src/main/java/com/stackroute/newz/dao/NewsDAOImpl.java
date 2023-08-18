package com.stackroute.newz.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.newz.model.News;


/*
 * This class is implementing the NewsDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */

@Transactional
@Repository
public class NewsDAOImpl implements NewsDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	
	@Autowired
	private SessionFactory sessionfactory;
	
	public NewsDAOImpl(SessionFactory sessionFactory) {
		this.sessionfactory= sessionFactory;
	}

	/*
	 * Save the news in the database(news) table.
	 */
	
	public boolean addNews(News news) {
		try {
		news.setPublishedAt(LocalDateTime.now());
		sessionfactory.getCurrentSession().save(news);
		sessionfactory.getCurrentSession().flush();
		return true;
		}catch(Exception e) {
			return false;
		}
		
	}

	/*
	 * retrieve all existing news sorted by created Date in descending
	 * order(showing latest news first)
	 */

	@SuppressWarnings("unchecked")
	public List<News> getAllNews() {
		List<News> newsList = sessionfactory.getCurrentSession().createQuery("From News order by publishedAt desc").list();
		sessionfactory.getCurrentSession().flush();
		return newsList;
				
	}

	/*
	 * retrieve specific news from the database(news) table
	 */
	
	public News getNewsById(int newsId) {
		News foundNews = sessionfactory.getCurrentSession().load(News.class, newsId);
		Hibernate.initialize(foundNews);
		sessionfactory.getCurrentSession().flush();
		return foundNews;
	}

	
	public boolean updateNews(News upNews) {
		try {
			News news = this.sessionfactory.getCurrentSession().load(News.class, upNews.getNewsId());
			news.setAuthor(upNews.getAuthor());
			news.setContent(upNews.getContent());
			news.setDescription(upNews.getDescription());
			news.setPublishedAt(upNews.getPublishedAt());
			news.setName(upNews.getName());

			this.sessionfactory.getCurrentSession().save(news);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Remove the news from the database(news) table.
	 */
	
	public boolean deleteNews(int newsId) {
		try {
			News n = (News) sessionfactory.getCurrentSession().load(News.class,newsId);
			sessionfactory.getCurrentSession().delete(n);
			sessionfactory.getCurrentSession().flush();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}