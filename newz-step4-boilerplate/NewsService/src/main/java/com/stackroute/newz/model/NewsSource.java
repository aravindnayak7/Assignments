package com.stackroute.newz.model;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * The class "NewsSource" will be acting as the data model. 
 */
@EntityScan
public class NewsSource {
	/*
	 * 
	 * Out of these fields, the
	 * field sourceId should be the key. This class should
	 * also contain the getters and setters for the fields along with the no-arg ,
	 * parameterized constructor and toString method. 
	 */
	@Id
	private String sourceId;
	private String sourceName;
	
	
	public NewsSource(String sourceId, String sourceName) {
		super();
		this.sourceId=sourceId;
		this.sourceName=sourceName;
	}


	public NewsSource() {
		
	}


	public String getSourceId() {
		return sourceId;
	}


	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}


	public String getSourceName() {
		return sourceName;
	}


	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}


	@Override
	public String toString() {
		return "NewsSource [sourceId=" + sourceId + ", sourceName=" + sourceName + "]";
	}
	
	
}
