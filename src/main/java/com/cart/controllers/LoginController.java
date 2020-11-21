package com.cart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.exception.ResourceNotFoundException;
import com.cart.model.User;
import com.cart.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> authUser(@RequestBody User user) {
		User loginUser = this.userService.findByUserName(user.getUsername(), user.getPassword());
		if(loginUser != null) {
			return ResponseEntity.accepted().body(loginUser);
		} else {
			new ResourceNotFoundException("User not found");
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED); 
		}
		
	}
}
