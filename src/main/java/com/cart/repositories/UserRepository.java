package com.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//User findByUserName(String userName);
}
