package com.reflecta.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.reflecta.entity.Users;

public class UserService {

	@Autowired
	private Users user;
	
	public String givename(String name)
	{
		name="HI";
		user.setName(name);
		return name;
		
	}

	public Users getUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
