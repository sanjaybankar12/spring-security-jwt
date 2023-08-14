package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.config.JwtConfig;
import com.spring.entity.User;
import com.spring.repository.UserDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
	
	@Autowired
	private JwtConfig jwtConfig;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public String getTokenForValidUser(User user) {
		String token = null;

		User u = this.userDetailsRepository.findByUsername(user.getUsername());
		if (u == null) {
			throw new UsernameNotFoundException("username not found");
		} else {
			log.info(user.getUsername());
			try {
				Authentication authenticate = this.authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
				if (authenticate.isAuthenticated()) {
					token = this.jwtConfig.generateToken(user.getUsername());
				} else {
					throw new UsernameNotFoundException("Invalid username or password");
				}
			} catch (AuthenticationException ex) {
				throw new UsernameNotFoundException("Invalid username or password");
			}
		}
		return token;
	}

}
