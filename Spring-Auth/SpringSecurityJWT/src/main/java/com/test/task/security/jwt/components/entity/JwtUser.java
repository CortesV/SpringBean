package com.test.task.security.jwt.components.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple Java object that represent jwt user
 * 
 * @author cortes
 *
 */

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 1215456216545L;

	private final Integer id;
	private final String username;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	private final Boolean isActive;

	public JwtUser(Integer id, String username, String password, Collection<? extends GrantedAuthority> authorities,
			Boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.isActive = enabled;
	}

	@JsonIgnore
	public Integer getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

}
