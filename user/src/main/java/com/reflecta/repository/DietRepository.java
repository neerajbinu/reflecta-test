package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.Diet;

public interface DietRepository extends JpaRepository<Diet, Long> {
}
