package com.stackroute.newz.repository;

import java.util.List;

import com.stackroute.newz.model.News;


/*
 * Annotate the class with @Repository annotation. 
 * This class contains the code for data storage interactions and methods 
 * of this class will be used by other parts of the applications such
 * as Controllers and Test Cases
 * */
public class NewsRepository {
	
	/* Declare a variable called "newsList" to store all the news. */
    private List<News> newsList;

    public NewsRepository() {
    	/* Initialize the variable using proper data type */
    }

    /* This method should return all the news in the list */
    public List<News> getNewsList() {
        return null;
    }

    /* This method should set the list variable with new list of news */
    public void setNewsList(List<News> newsList) {
        
    }

    /*
	 * This method should News object as argument and add the new news object into
	 * list
	 */
    public  void addNews(News news) {
        
    }
    
    /* This method should return the list of news */
    public List<News> getAllNews() {
        return null;
    }
    
    /* This method should delete a specified news from the list */
    public boolean deleteNews(int newsId) {
       
        return false;
       

    }
  
}
