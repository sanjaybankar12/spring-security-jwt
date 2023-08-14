package com.spring.service;

import com.spring.entity.User;

public interface JwtService {

	String getTokenForValidUser(User user);

}
