package com.test.task.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.task.security.jwt.service.JwtTokenUtil;
import com.test.task.security.jwt.service.JwtUserDetailsServiceImpl;

public class TokenUtilTest {

	private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;
	private JwtTokenUtil jwtTokenUtil;
	private UserDetails userDetails;
	private Device device;
	private String token;
	private String username;
	private Date date;

	@Before
	public void setUp() {
		jwtTokenUtil = mock(JwtTokenUtil.class);
		device = mock(Device.class);
		jwtUserDetailsServiceImpl = mock(JwtUserDetailsServiceImpl.class);

		token = "eyJhbGciOiJIUzUxMiJ9";
		date = new Date();
		username = "test";
	}

	@Test
	public void getUsernameFromTokenTest() throws ServletException, IOException {
		when(jwtTokenUtil.getUsernameFromToken(token)).thenReturn(username);
		Assert.assertEquals(username, jwtTokenUtil.getUsernameFromToken(token));
		verify(jwtTokenUtil, times(1)).getUsernameFromToken(token);
	}

	@Test
	public void getCreatedDateFromTokenTest() throws ServletException, IOException {
		when(jwtTokenUtil.getCreatedDateFromToken(token)).thenReturn(date);
		Assert.assertEquals(date, jwtTokenUtil.getCreatedDateFromToken(token));
		verify(jwtTokenUtil, times(1)).getCreatedDateFromToken(token);
	}

	@Test
	public void getExpirationDateFromTokenTest() throws ServletException, IOException {
		when(jwtTokenUtil.getExpirationDateFromToken(token)).thenReturn(date);
		Assert.assertEquals(date, jwtTokenUtil.getExpirationDateFromToken(token));
		verify(jwtTokenUtil, times(1)).getExpirationDateFromToken(token);
	}

	@Test
	public void getClaimsFromTokenTest() throws ServletException, IOException {
		userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
		when(jwtTokenUtil.generateToken(userDetails, device)).thenReturn(token);
		Assert.assertEquals(token, jwtTokenUtil.generateToken(userDetails, device));
		verify(jwtTokenUtil, times(1)).generateToken(userDetails, device);
	}

	@Test
	public void validateTokenTest() throws ServletException, IOException {
		userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
		when(jwtTokenUtil.validateToken(token, userDetails)).thenReturn(true);
		Assert.assertEquals(true, jwtTokenUtil.validateToken(token, userDetails));
		verify(jwtTokenUtil, times(1)).validateToken(token, userDetails);
	}

}
