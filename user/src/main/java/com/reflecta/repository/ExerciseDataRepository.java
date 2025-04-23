package com.reflecta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.ExerciseData;
import com.reflecta.entity.Users;
import com.reflecta.enums.ExerciseType;

public interface ExerciseDataRepository extends JpaRepository<ExerciseData, Long> {
	
	// Get all exercises by user
    List<ExerciseData> findByUser(Users user);

    // Get all exercises by user and date range
    List<ExerciseData> findByUserAndDateBetween(Users user, LocalDate startDate, LocalDate endDate);

    // Get all exercises by user and goal
    List<ExerciseData> findByUserAndGoal(Users user, com.reflecta.entity.Goal goal);

    // Get all exercises by user and exerciseType
    List<ExerciseData> findByUserAndExerciseType(Users user,ExerciseType exerciseType);

	boolean existsByStravaActivityId(String string); //method added for Strava
	
}