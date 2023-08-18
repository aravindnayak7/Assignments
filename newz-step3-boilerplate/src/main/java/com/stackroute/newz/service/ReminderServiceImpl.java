package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.Reminder;
import com.stackroute.newz.repository.ReminderRepository;
import com.stackroute.newz.service.ReminderService;
import com.stackroute.newz.util.exception.ReminderNotExistsException;

/*
 * This class is implementing the ReminderService interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */
@Service
public class ReminderServiceImpl implements ReminderService {

	/*
	 * Autowiring should be implemented for the ReminderRepository.
	 */
	@Autowired
	private ReminderRepository remiderRepo;

	/*
	 * Add a new reminder.
	 */
	public Reminder addReminder(Reminder reminder) {
		Optional<Reminder> r =remiderRepo.findById(reminder.getReminderId());
		Reminder rem = null;
		
			rem = remiderRepo.save(reminder);
			return rem;
	}

	/*
	 * Update an existing reminder by it's reminderId. Throw ReminderNotExistsException 
	 * if the reminder with specified reminderId does not exist.
	 */
	public Reminder updateReminder(Reminder reminder) throws ReminderNotExistsException {
		Reminder r = remiderRepo.getOne(reminder.getReminderId());
		Reminder addrem=null;
		if(r!=null) {
			
			r.setNews(reminder.getNews());
			r.setSchedule(reminder.getSchedule());
			
			addrem = remiderRepo.saveAndFlush(addrem);
			return addrem;
		}else {
			throw new ReminderNotExistsException();
		}
	}

	/*
	 * Delete an existing reminder by it's reminderId. Throw ReminderNotExistsException if 
	 * the reminder with specified reminderId does not exist.
	 */
	public void deleteReminder(int reminderId) throws ReminderNotExistsException {
		Reminder r = remiderRepo.getOne(reminderId);
		if(r!=null) {
			remiderRepo.deleteById(null);
		}else {
			throw new ReminderNotExistsException();
		}
	
	}

	/*
	 * Retrieve an existing reminder by it's reminderId. Throw ReminderNotExistsException 
	 * if the reminder with specified reminderId does not exist.
	 */
	public Reminder getReminder(int reminderId) throws ReminderNotExistsException {
		Optional<Reminder> r = remiderRepo.findById(reminderId);
		if(r.isPresent()) {
			return r.get();
		}else
			throw new ReminderNotExistsException();
	}

	/*
	 * Retrieve all existing reminders
	 */
	public List<Reminder> getAllReminders() {
		return remiderRepo.findAll();
	}

}
