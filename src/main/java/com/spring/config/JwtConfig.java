package com.spring.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtConfig {
	
	private final String SECRET = "021fb044d1c216bc015a04fce9067ffb109253369c8c0a82ffe5941aa4ed7fa7";

	public String generateToken(String username) {
		Map<String, String> claims = new HashMap<>();
		return createToken(claims, username);
	}
	
	public String createToken(Map<String, String> claims, String username) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 5))// 5min
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public SecretKey getKey() {
		byte[] keyarr = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyarr);		
	}
}
