package com.test.task.security.jwt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.test.task.components.entity.Role;
import com.test.task.components.entity.User;
import com.test.task.security.jwt.components.entity.JwtUser;

@Service
public class JwtUserFactory {

	/**
	 * Method create JwtUser
	 * 
	 * @param user
	 * @return
	 */
	public JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getUserName(), user.getPassword(),
				mapToGrantedAuthorities(user.getRoles()), user.getIsActive());
	}

	/**
	 * Method that collect all roles of user
	 * 
	 * @param authorities
	 * @return
	 */
	private List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName()))
				.collect(Collectors.toList());
	}		
}
