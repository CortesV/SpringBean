package com.test.task.components.interfaces;

import java.util.List;

import com.test.task.components.entity.User;

public interface IUser {

	/**
	 * Get user by username
	 * 
	 * @param username
	 *            username - username of user in database
	 * @return return information about of user
	 */
	public User findByUsername(String username);

	/**
	 * Method that return all users in database
	 * 
	 * @return return - list of users
	 */
	public List<User> getUsers();

	/**
	 * Delete user by it's id
	 * 
	 * @param id
	 *            id - id of user in database
	 */
	public void deleteUser(Integer id);

	/**
	 * Update user's information
	 * 
	 * @param id
	 *            id - id of user in database
	 * @param user
	 *            user - all information about user that will write to database
	 */
	public void updateUser(Integer id, User user);

	/**
	 * Get user by id
	 * 
	 * @param id
	 *            id - id of user in database
	 * @return return information about of user
	 */
	public User getUserById(Integer id);

	/**
	 * Save user to database
	 * 
	 * @param user
	 *            user - all information about user that will save to database
	 */
	public void saveUser(User user);
}
