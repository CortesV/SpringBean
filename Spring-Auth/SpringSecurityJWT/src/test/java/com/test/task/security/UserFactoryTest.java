package com.test.task.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.test.task.components.entity.User;
import com.test.task.components.service.UserDao;
import com.test.task.security.jwt.components.entity.JwtUser;
import com.test.task.security.jwt.service.JwtUserFactory;

public class UserFactoryTest {

	private JwtUserFactory jwtUserFactory;
	private User user;
	private JwtUser jwtUser;
	private UserDao userDao;

	@Before
	public void setUp() {
		userDao = mock(UserDao.class);
		jwtUserFactory = mock(JwtUserFactory.class);
		user = userDao.findByUsername("test");
		jwtUser = mock(JwtUser.class);
	}

	@Test
	public void createTest() throws ServletException, IOException {
		when(jwtUserFactory.create(user)).thenReturn(jwtUser);
		Assert.assertEquals(jwtUser, jwtUserFactory.create(user));
		verify(jwtUserFactory, times(1)).create(user);
	}
}
