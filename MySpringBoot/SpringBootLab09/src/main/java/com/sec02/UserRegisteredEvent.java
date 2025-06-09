package com.sec02;

import lombok.Data;

public @Data class UserRegisteredEvent { 
	
	
	private final String username;

	public UserRegisteredEvent(String username) {

		this.username = username;
	} 
	
	public String getUsername() {
		return username;
	}
} 
