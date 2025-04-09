package com.reflecta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reflecta.entity.Users;
import com.reflecta.service.UsersService;
	
	@RestController
	@RequestMapping("/api/users")
	public class UsersController {

	    @Autowired
	    private UsersService userService;

	    @PostMapping
	    public Users createUser(@RequestBody Users user) {
	        return userService.createUser(user);
	    }

	    @GetMapping("/{id}")
	    public Users getUser(@PathVariable Long id) {
	        return userService.getUserById(id);
	    }
	    
	    @GetMapping("/email/{email}")
	    public Users getUserByEmail(@PathVariable String email) {
	        return userService.getUserByEmail(email);
	    }

	    @GetMapping
	    public List<Users> getAllUsers() {
	        return userService.getAllUsers();
	    }

	    @PutMapping("/{id}")
	    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
	        return userService.updateUser(id, user);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	    }
	}


