package com.stackroute.newz.model;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * The class "Reminder" will be acting as the data model. 
 */
@EntityScan
public class Reminder {
	/*
	 * 
	 * Out of these fields, the
	 * field reminderId should be the key. This class should
	 * also contain the getters and setters for the fields along with the no-arg ,
	 * parameterized constructor and toString method. 
	 */
	@Id
	private String reminderId;
	private Date schedule;
	
	public Reminder(String reminderId, Date schedule) {
		super();
		this.reminderId = reminderId;
		this.schedule = schedule;
	}
	public String getReminderId() {
		return reminderId;
	}
	public void setReminderId(String reminderId) {
		this.reminderId = reminderId;
	}
	public Date getSchedule() {
		return schedule;
	}
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
	@Override
	public String toString() {
		return "Reminder [reminderId=" + reminderId + ", schedule=" + schedule + "]";
	}

	

}
