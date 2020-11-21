package com.cart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cart.model.User;
import com.cart.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByUserName(String userName, String pass) {
		List<User> userList = userRepository.findAll();
		for(User userInfo : userList) {
			if(userInfo.getUsername().equalsIgnoreCase(userName)
					&& userInfo.getPassword().equalsIgnoreCase(pass)) {
				//userInfo.setPassword("");
				return userInfo;
			}
		}
		
		return null;
	}

	@Override
	public void createUser(User user) {
		this.userRepository.save(user);
	}

}
