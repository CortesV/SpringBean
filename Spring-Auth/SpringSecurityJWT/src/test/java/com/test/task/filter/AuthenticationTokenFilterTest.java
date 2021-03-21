package com.test.task.filter;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.test.task.security.jwt.filter.JwtAuthenticationTokenFilter;

public class AuthenticationTokenFilterTest {

	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private FilterChain chain;
	
	@Before
	public void setUp() {
		jwtAuthenticationTokenFilter = mock(JwtAuthenticationTokenFilter.class);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		chain = mock(FilterChain.class);		
	}
	
	@Test
	public void filterTest() throws ServletException, IOException {
		doNothing().when(jwtAuthenticationTokenFilter).doFilterInternal(request, response, chain);
		jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);
		verify(jwtAuthenticationTokenFilter, times(1)).doFilterInternal(request, response, chain);
	}
}
