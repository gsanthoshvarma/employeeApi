package com.sample.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService{

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("admin".equals(username)) {
			List<UserRoles> userRoles = new ArrayList<>();
			return new MyUserDetails("admin","admin",userRoles);
		}else {
			throw new UsernameNotFoundException("Invaild Uname");
		}

		
	}

}
