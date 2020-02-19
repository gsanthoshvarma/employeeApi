package com.sample.security;

import org.springframework.security.core.GrantedAuthority;

public class UserRoles implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Override
	public String getAuthority() {
		return "ADMIN";
	}

}
