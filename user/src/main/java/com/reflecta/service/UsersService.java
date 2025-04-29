package com.reflecta.service;

import com.reflecta.entity.Users;
import java.util.List;

public interface UsersService {
    Users createUser(Users user);
    Users getUserById(Long id);
    Users getUserByEmail(String email);
    List<Users> getAllUsers();
    Users updateUser(Long id, Users user);
    void deleteUser(Long id);
	double calculateBMR(Users user);
	Double getActivityMultiplier(Users user);
}
