package com.test.task.servicetest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.test.task.components.entity.User;
import com.test.task.components.service.UserDao;
import com.test.task.service.UserService;

public class UserServiceTest {

	private UserService userService;
	private User user;
	private UserDao userDao;
	private List<User> users;

	@Before
	public void setUp() {
		user = new User("test", "test", true);
		userDao = mock(UserDao.class);
		userService = new UserService();
		userService.setiUser(userDao);
		users = new ArrayList<>();
		users.add(user);
	}

	@Test
	public void getUsersTest() {
		when(userDao.getUsers()).thenReturn(users);
		Assert.assertEquals(userService.getUsers().size(), users.size());
		verify(userDao, times(1)).getUsers();
	}

	@Test
	@Rollback(false)
	public void deleteUserTest() {
		doNothing().when(userDao).deleteUser(1);
		userService.deleteUser(1);
		verify(userDao, times(1)).deleteUser(1);
	}

	@Test
	@Rollback(false)
	public void updateUserTest() {
		doNothing().when(userDao).updateUser(1, user);
		userService.updateUser(1, user);
		verify(userDao, times(1)).updateUser(1, user);
	}

	@Test
	public void getUserByIdTest() {
		when(userDao.getUserById(1)).thenReturn(user);
		Assert.assertEquals(userService.getUserById(1).getUserName(), user.getUserName());
		verify(userDao, times(1)).getUserById(1);
	}

	@Test
	@Rollback(false)
	public void saveUserTest() {
		doNothing().when(userDao).saveUser(user);
		userService.saveUser(user);
		verify(userDao, times(1)).saveUser(user);
	}

	@Test
	public void getUserByUserName() {
		String username = "test";
		when(userDao.findByUsername(username)).thenReturn(user);
		Assert.assertEquals(userService.findByUsername(username).getUserName(), user.getUserName());
		verify(userDao, times(1)).findByUsername(username);
	}

}
