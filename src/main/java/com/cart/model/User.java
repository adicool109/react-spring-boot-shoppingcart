package com.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long userId;
	
	@Column(nullable = false, length = 200, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String fullname;
	
	@Column(nullable = false)
	private String password;
	
	public User() { }
	
	public User(String userName, String fullName, String password) {
		this.username = userName;
		this.fullname = fullName;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
