package com.test.task.security;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

import com.test.task.security.jwt.service.JwtAuthenticationEntryPoint;

public class AuthenticationEntryPointTest {

	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private AuthenticationException authException;
	
	@Before
	public void setUp() {
		jwtAuthenticationEntryPoint = mock(JwtAuthenticationEntryPoint.class);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		authException = mock(AuthenticationException.class);
	}
	
	@Test
	public void commenceTest() throws ServletException, IOException {
		doNothing().when(jwtAuthenticationEntryPoint).commence(request, response, authException);
		jwtAuthenticationEntryPoint.commence(request, response, authException);
		verify(jwtAuthenticationEntryPoint, times(1)).commence(request, response, authException);
	}
}
