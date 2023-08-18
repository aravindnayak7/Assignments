package com.stackroute.newz.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
	 * 
	 * The data type for sourceCreationDate field should be LocalDateTime. 
	 * Please add @JsonSerialize(using = ToStringSerializer.class) for this field
	 */
	@Id
	private int sourceId;
	private String sourceName;
	private String sourceDesc;
	private String sourceCreatedBy;
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime sourceCreationDate;
	
	
	public NewsSource(int sourceId, String sourceName, String sourceDesc, String sourceCreatedBy,
			LocalDateTime sourceCreationDate) {
		super();
		this.sourceId=sourceId;
		this.sourceName=sourceName;
		this.sourceDesc=sourceDesc;
		this.sourceCreationDate=sourceCreationDate;
		this.sourceCreationDate=sourceCreationDate;
	}


	public NewsSource() {
	}


	public int getSourceId() {
		return sourceId;
	}


	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}


	public String getSourceName() {
		return sourceName;
	}


	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}


	public String getSourceDesc() {
		return sourceDesc;
	}


	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}


	public String getSourceCreatedBy() {
		return sourceCreatedBy;
	}


	public void setSourceCreatedBy(String sourceCreatedBy) {
		this.sourceCreatedBy = sourceCreatedBy;
	}


	public LocalDateTime getSourceCreationDate() {
		return sourceCreationDate;
	}


	public void setSourceCreationDate(LocalDateTime sourceCreationDate) {
		this.sourceCreationDate = sourceCreationDate;
	}


	@Override
	public String toString() {
		return "NewsSource [sourceId=" + sourceId + ", sourceName=" + sourceName + ", sourceDesc=" + sourceDesc
				+ ", sourceCreatedBy=" + sourceCreatedBy + ", sourceCreationDate=" + sourceCreationDate + "]";
	}
	

	
}
