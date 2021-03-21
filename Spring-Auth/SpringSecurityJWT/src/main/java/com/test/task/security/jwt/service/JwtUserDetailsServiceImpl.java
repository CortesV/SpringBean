package com.test.task.security.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.task.components.entity.User;
import com.test.task.service.UserService;

/**
 * Service that implement UserDetailsService and create JwtUserFactory
 * 
 * @author cortes
 *
 */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	private static final String NO_FOUND_USER = "No user found with username '%s'.";

	
	private UserService userService;
	
	private JwtUserFactory jwtUserFactory;

	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	};
	
	@Autowired
	public void setJwtUserFactory(JwtUserFactory jwtUserFactory){
		this.jwtUserFactory = jwtUserFactory;
	};
	
	/**
	 * Method that get user from database and create JwtUserFactory
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format(NO_FOUND_USER, username));
		} else {
			return jwtUserFactory.create(user);
		}
	}
}
