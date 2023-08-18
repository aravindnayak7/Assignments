package com.stackroute.newz.model;

import java.time.LocalDateTime;

/* 
 * Annotate the class with @Component annotation. 
 * @Component annotation is used to crete singleton instance of of the class
 *
 */


/*
 * The class "News" will be acting as the data model for the News data in the ArrayList.
 */
public class News {
	
	/*
	 * This class should have five fields (newsId, title, author,
	 * description, content and publishedAt). This class should also contain the getters and
	 * setters for the fields. The value of publishedAt should not be accepted from
	 * the user but should be always initialized with the system date
	 */
    

	
    public News() {
    	/* default constructor */
    }

    
    /* All the getters/setters definition should be implemented here */
    
    public int getNewsId() {
        return 0;
    }

    public void setNewsId(int newsId) {
        
    }

    public String getTitle() {
        return null;
    }

    public void settitle(String title) {
        
    }

    public String getAuthor() {
        return null;
    }

    public void setAuthor(String author) {
        
    }

    public String getDescription() {
        return null;
    }

    public void setDescription(String description) {
        
    }

    public LocalDateTime getPublishedAt() {
        return null;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        
    }

    public String getContent() {
        return null;
    }

    public void setContent(String content) {
        
    }
    
    /* Override the toString() method */
    @Override
    public String toString() {
        return null;
    }
}
