package com.test.task.security.jwt.components.entity;

import java.io.Serializable;

/**
 * Simple Java object that represent jwt authentication response
 * 
 * @author cortes
 *
 */

public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private final String token;

	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
