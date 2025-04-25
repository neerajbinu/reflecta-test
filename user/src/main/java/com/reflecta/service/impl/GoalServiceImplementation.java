package com.reflecta.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reflecta.dto.GoalRequestDTO;
import com.reflecta.entity.Goal;
import com.reflecta.entity.Users;
import com.reflecta.enums.GoalStatus;
import com.reflecta.enums.GoalType;
import com.reflecta.repository.GoalRepository;
import com.reflecta.repository.UsersRepository;
import com.reflecta.service.GoalService;

@Service
public class GoalServiceImplementation implements GoalService {
	
	@Autowired
	private GoalRepository gr;
	
	@Autowired
	private UsersRepository ur;

	@Override
	public Goal createGoal(Long userId, GoalRequestDTO goalRequestDTO) {
		//checking if a User exits
		 	Users user = ur.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

		 //creating a Goal
			Goal goal=new Goal();
			goal.setType(goalRequestDTO.getType());
		    goal.setFrequency(goalRequestDTO.getFrequency());
		    goal.setTargetHours(goalRequestDTO.getTargetHours());
		    goal.setCurrentProgress(0); // Initial progress is 0
		    goal.setStartDate(goalRequestDTO.getStartDate());
		    goal.setEndDate(goalRequestDTO.getEndDate());
		    goal.setStatus(GoalStatus.ONGOING); // Set default status
		    goal.setUser(user);
		    
		    return gr.save(goal);
		    
	
	}

	@Override
	public Optional<Goal> getActiveSleepGoalForUser(Long userId, LocalDate date) {
		return gr.findByUserIdAndTypeAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
		        userId,
		        GoalType.SLEEP,
		        GoalStatus.ONGOING,
		        date,
		        date
		    );  //returns an Optional<Goal>
	}

	@Override
	public List<Goal> getGoalsByUser(Long userId) {
		  return gr.findByUserId(userId);
	}
	
	

}
