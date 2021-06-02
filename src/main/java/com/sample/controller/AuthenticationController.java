package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.UserValue;
import com.sample.security.JwtTokenUtil;
import com.sample.security.MyUserDetailsService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private MyUserDetailsService userDetails;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	@RequestMapping(value="/authenticate/",method=RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public String authenticate(@RequestBody UserValue userValue){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userValue.getUsername(), userValue.getPassword()));	
		}catch (BadCredentialsException exception) {
			throw new BadCredentialsException("Invaild username and password");
		}
		final UserDetails user = userDetails.loadUserByUsername(userValue.getUsername());
		String jwtToken = jwtTokenUtil.generateToken(user);
		return jwtToken;
	}

}
