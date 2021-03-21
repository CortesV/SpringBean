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

import com.test.task.security.jwt.components.entity.JwtUser;
import com.test.task.security.jwt.service.JwtUserDetailsServiceImpl;

public class UserDetailsServiceTest {

	private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;
	private JwtUser jwtUser;

	@Before
	public void setUp() {
		jwtUserDetailsServiceImpl = mock(JwtUserDetailsServiceImpl.class);
		jwtUser = mock(JwtUser.class);
	}

	@Test
	public void commenceTest() throws ServletException, IOException {
		String username = "test";
		when(jwtUserDetailsServiceImpl.loadUserByUsername(username)).thenReturn(jwtUser);
		Assert.assertEquals(jwtUser, jwtUserDetailsServiceImpl.loadUserByUsername(username));
		verify(jwtUserDetailsServiceImpl, times(1)).loadUserByUsername(username);
	}
}
