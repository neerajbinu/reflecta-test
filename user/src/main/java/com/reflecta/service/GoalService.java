package com.reflecta.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.reflecta.dto.GoalRequestDTO;
import com.reflecta.entity.Goal;

public interface GoalService {
	
	Goal createGoal(Long userId,GoalRequestDTO goalRequestDTO);
	Optional<Goal> getActiveSleepGoalForUser(Long userId, LocalDate date);
	 List<Goal> getGoalsByUser(Long userId);
	void updateGoalProgress(Goal goal, Double progress);
	Goal createDietGoal(Long userId, GoalRequestDTO goalRequestDTO);
	Goal createExerciseGoal(Long userId, GoalRequestDTO goalRequestDTO);
	Goal createSleepGoal(Long userId, GoalRequestDTO goalRequestDTO);
	//void updateGoalProgress(Goal goal,Double progress);
	

}
