package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.config.JwtConfig;
import com.spring.entity.User;
import com.spring.service.JwtService;

@RestController
@RequestMapping("/jwt")
public class JwtController {
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/token")
	public String jwtToken(@RequestBody User user) {		
		return this.jwtService.getTokenForValidUser(user);		
	}
}
