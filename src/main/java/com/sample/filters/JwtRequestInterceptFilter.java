package com.sample.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sample.security.JwtTokenUtil;
import com.sample.security.MyUserDetailsService;

@Component
public class JwtRequestInterceptFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Invoking each request");
		String clientJwt = null;
		String username = null;
		UserDetails userDetails = null;
		String authorizationHeaderValue = request.getHeader("Authorization");
		if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer")) {
			clientJwt = authorizationHeaderValue.substring(7);
			username = jwtTokenUtil.extractUsername(clientJwt);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			userDetails = userDetailsService.loadUserByUsername(username);
			if(jwtTokenUtil.validateToken(clientJwt, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			
		}
		filterChain.doFilter(request, response);
	}
}
