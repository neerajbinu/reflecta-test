package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
