package com.stackroute.newz.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/*
 * The class "News" will be acting as the data model. 
 */
@EntityScan
public class News {
	
	/*
	 * 
	 * Out of these fields, the
	 * field newsId should be the key. This class should
	 * also contain the getters and setters for the fields along with the no-arg ,
	 * parameterized constructor and toString method. 
	 * 
	 * The data type for publishedAt field should be LocalDateTime. 
	 * Please add @JsonSerialize(using = ToStringSerializer.class) for this field
	 */
	@Id
	
	private int newsId;
	private String title;
	private String author;
	private String description;
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime publishedAt;
	private String content;
	private String url;
	private String urlToImage;
	private Reminder reminder;
	private NewsSource newsSource;
	
	public News(int newsId, String title, String author, String description, LocalDateTime publishedAt, String content,
			String url, String urlToImage, Reminder reminder, NewsSource newsSource) {
		super();
		this.newsId=newsId;
		this.title=title;
		this.author=author;
		this.description=description;
		this.publishedAt=publishedAt;
		this.content=content;
		this.url=url;
		this.urlToImage=urlToImage;
		this.reminder=reminder;
		this.newsSource=newsSource;
		
	}

	public News() {
		
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	public NewsSource getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(NewsSource newsSource) {
		this.newsSource = newsSource;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", title=" + title + ", author=" + author + ", description=" + description
				+ ", publishedAt=" + publishedAt + ", content=" + content + ", url=" + url + ", urlToImage="
				+ urlToImage + ", reminder=" + reminder + ", newsSource=" + newsSource + "]";
	}

	

}
