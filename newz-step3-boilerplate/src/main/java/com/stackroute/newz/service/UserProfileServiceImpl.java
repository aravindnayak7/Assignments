package com.stackroute.newz.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.UserProfile;
import com.stackroute.newz.repository.UserProfileRepository;
import com.stackroute.newz.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.newz.util.exception.UserProfileNotExistsException;

/*
 * This class is implementing the UserProfileRepository interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */
@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	/*
	 * Autowiring should be implemented for the UserProfileRepository.
	 */
	@Autowired
	private UserProfileRepository userprofilerepo;


	/*
	 * Add a new user. Throw UserProfileAlreadyExistsException if the userProfile with specified
	 * userId already exists.
	 */
	public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {
		Optional<UserProfile> userExists = userprofilerepo.findById(user.getUserId());
		UserProfile newUser = null;
		if (userExists.isPresent()) {
			throw new UserProfileAlreadyExistsException();
		}else {
			newUser = userprofilerepo.save(user);
		}
		return newUser;
	}

	/*
	 * Update an existing userProfile by it's userId. Throw UserProfileNotExistsException 
	 * if the userProfile with specified userId does not exist.
	 */
	public UserProfile updateUserProfile(UserProfile user, String userId) 
			throws UserProfileNotExistsException {
		UserProfile p = userprofilerepo.getOne(userId);
		UserProfile up = null;
		if(p!=null) {
			
			p.setLastName(user.getLastName());
			p.setFirstName(user.getFirstName());
			p.setCreateAt(LocalDateTime.now());
			p.setContact(user.getContact());
		
			up = userprofilerepo.saveAndFlush(p);
			return up;
			}
		else {
			throw new UserProfileNotExistsException();
		}
	}

	
	/*
	 * Delete an existing userProfile by it's userId. Throw UserProfileNotExistsException if 
	 * the userProfile with specified userId does not exist.
	 */
	public void deleteUserProfile(String userId) throws UserProfileNotExistsException {
		UserProfile delP = userprofilerepo.getOne(userId);
		if(delP!=null) {
			userprofilerepo.deleteById(userId);
		}else {
			throw new UserProfileNotExistsException();
		}
		
	}
	
	
	/*
	 * Retrieve an existing userProfile by it's userId. Throw UserProfileNotExistsException 
	 * if the userProfile with specified userId does not exist.
	 */
	public UserProfile getUserProfile(String userId) throws UserProfileNotExistsException {
		Optional<UserProfile> r = userprofilerepo.findById(userId);
		if(r.isPresent()) {
			return r.get();
		}else
			throw new UserProfileNotExistsException();
	}

	/*
	 * Retrieve all existing userProfiles
	 */
	public List<UserProfile> getAllUserProfiles() {
		
		return userprofilerepo.findAll();
	}

}
