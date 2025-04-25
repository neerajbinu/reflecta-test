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
	

}
