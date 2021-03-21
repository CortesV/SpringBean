package com.test.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.task.components.entity.User;
import com.test.task.components.interfaces.IUser;

/**
 * Service for CRUD of User
 * 
 * @author cortes
 *
 */

@Service
public class UserService {

	private IUser iUser;

	@Autowired
	public void setiUser(IUser iUser) {
		this.iUser = iUser;
	}

	/**
	 * Get user by username
	 * 
	 * @param username
	 *            username - username of user in database
	 * @return return information about of user
	 */
	public User findByUsername(String username) {
		return iUser.findByUsername(username);
	}

	/**
	 * Method that return all users in database
	 * 
	 * @return return - list of users
	 */
	public List<User> getUsers() {
		return iUser.getUsers();
	}

	/**
	 * Delete user by it's id
	 * 
	 * @param id
	 *            id - id of user in database
	 */
	public void deleteUser(Integer id) {
		iUser.deleteUser(id);
	}

	/**
	 * Update user's information
	 * 
	 * @param id
	 *            id - id of user in database
	 * @param user
	 *            user - all information about user that will write to database
	 */
	public void updateUser(Integer id, User user) {
		iUser.updateUser(id, user);
	}

	/**
	 * Get user by id
	 * 
	 * @param id
	 *            id - id of user in database
	 * @return return information about of user
	 */
	public User getUserById(Integer id) {
		return iUser.getUserById(id);
	}

	/**
	 * Save user to database
	 * 
	 * @param user
	 *            user - all information about user that will save to database
	 */
	public void saveUser(User user) {
		iUser.saveUser(user);
	}

}
