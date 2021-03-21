package com.test.task.security.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.test.task.security.jwt.components.entity.MutableHttpServletRequest;
import com.test.task.security.jwt.service.JwtTokenUtil;

/**
 * Represent filter for checking token
 * @author cortes
 *
 */

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = Logger.getLogger(JwtAuthenticationTokenFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;

	/**
	 * Method that validate token
	 */
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String authToken = request.getHeader(this.tokenHeader);

		if (authToken == null) {
			authToken = request.getParameter("token");
		}

		if (authToken == null) {
			Cookie[] cookies = request.getCookies();
			authToken = cookies[0].getValue();
		}
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		LOGGER.info("checking authentication for user " + username);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {	
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
		mutableRequest.putHeader(this.tokenHeader, authToken);		
		chain.doFilter(mutableRequest, response);
	}

}
