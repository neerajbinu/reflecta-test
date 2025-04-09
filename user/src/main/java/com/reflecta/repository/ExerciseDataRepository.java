package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.ExerciseData;

public interface ExerciseDataRepository extends JpaRepository<ExerciseData, Long> {
	
}