package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.User;

public interface UserDetailsRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
