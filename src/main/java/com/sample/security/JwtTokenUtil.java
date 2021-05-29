package com.sample.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	private static final String SECRET_KEY = "lasya";
	
	public String createToken(Map<String, Object> claims,String subject) {
		return Jwts.builder().addClaims(claims)
		.setSubject(subject)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * 1))
		.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
		.compact();
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", userDetails.getUsername());
		claims.put("createdDate", new Date(System.currentTimeMillis()));
		claims.put("roles", userDetails.getAuthorities());
		return createToken(claims,userDetails.getUsername());
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public <T> T extractClaims(String token,Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String extractUsername(String token) {
		return extractClaims(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	public boolean isExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public boolean validateToken(String token,UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isExpired(token));
	}

}
