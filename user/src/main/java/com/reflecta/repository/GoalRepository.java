package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	
}