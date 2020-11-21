package com.cart.services;

import org.springframework.validation.annotation.Validated;

import com.cart.model.User;

@Validated
public interface UserService {
	
	User findByUserName(String userName, String password);
	
	void createUser(User user);
}
