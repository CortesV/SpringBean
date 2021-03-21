package com.test.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.task.components.entity.User;
import com.test.task.service.UserService;

/**
 * Controller for CRUD of Client
 * 
 * @author cortes
 *
 */

@RestController
@RequestMapping(value = "/rest/test/v1/user/")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Controller that return all users in database
	 * 
	 * @return return - list of users
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers() {
		return userService.getUsers();
	}

	/**
	 * Get user by id
	 * 
	 * @param id
	 *            id - id of user in database
	 * @return return information about of user
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}

	/**
	 * Save user to database
	 * 
	 * @param user
	 *            user - all information about user that will save to database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user) {
		userService.saveUser(user);
	}

	/**
	 * Delete user by it's id
	 * 
	 * @param id
	 *            id - id of user in database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}

	/**
	 * Update user's information
	 * 
	 * @param id
	 *            id - id of user in database
	 * @param user
	 *            user - all information about user that will write to database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		userService.updateUser(id, user);
	}
}
