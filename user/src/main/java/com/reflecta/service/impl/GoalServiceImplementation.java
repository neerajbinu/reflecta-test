package com.reflecta.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reflecta.dto.GoalRequestDTO;
import com.reflecta.entity.Goal;
import com.reflecta.entity.MealLog;
import com.reflecta.entity.Users;
import com.reflecta.enums.GoalMetric;
import com.reflecta.enums.GoalStatus;
import com.reflecta.enums.GoalType;
import com.reflecta.enums.WeightGoal;
import com.reflecta.repository.GoalRepository;
import com.reflecta.repository.MealLogRepository;
import com.reflecta.repository.UsersRepository;
import com.reflecta.service.GoalService;
import com.reflecta.service.UsersService;

@Service
public class GoalServiceImplementation implements GoalService {
	
	@Autowired
	private GoalRepository gr;
	
	@Autowired
	private UsersRepository ur;
	
	@Autowired
	private UsersService us;
	
	@Autowired
	private MealLogRepository mlr;
	
	@Override
	public Goal createGoal(Long userId, GoalRequestDTO goalRequestDTO) {
		
	    Goal savedGoal;
	    // Checking if a User exists
	    Users user = ur.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    // Creating a Goal
	    Goal goal = new Goal(); 
	    goal.setType(goalRequestDTO.getType());
	    goal.setFrequency(goalRequestDTO.getFrequency());
	    goal.setTarget(goalRequestDTO.getTarget());
	    goal.setCurrentProgress(0); // Initial progress is 0
	    goal.setStartDate(goalRequestDTO.getStartDate());
	    goal.setEndDate(goalRequestDTO.getEndDate());
	    goal.setStatus(GoalStatus.ONGOING); // Set default status
	    goal.setMetric(goalRequestDTO.getMetric());
	    goal.setUser(user);

	    // Set WeightGoal only if the metric is DIET (and the goal type is weight related)
	    if (goalRequestDTO.getMetric() == GoalMetric.CALORIES_CONSUMED && goalRequestDTO.getWeightGoal() != null) {
	        goal.setWeightGoal(goalRequestDTO.getWeightGoal()); // Set WeightGoal only for diet-related goals
	    } else {
	        goal.setWeightGoal(null); // Set as null for other metrics like exercise, sleep, etc.
	    }

	    // Additional logic specific to each goal type (DIET, EXERCISE, SLEEP)
	    switch (goalRequestDTO.getType()) {
	        case DIET:
	             savedGoal=createDietGoal(userId, goalRequestDTO);
	            break;

	        case EXERCISE:
	            savedGoal=createExerciseGoal(userId, goalRequestDTO);
	            break;

	        case SLEEP:
	        	savedGoal=createSleepGoal(userId, goalRequestDTO);
	            break;

	        default:
	            throw new IllegalArgumentException("Unsupported goal type: " + goalRequestDTO.getType());
	    }

	
		return savedGoal;
	}

	@Override
	public Goal createExerciseGoal(Long userId, GoalRequestDTO goalRequestDTO) {
		// Checking if a User exists
		Users user = ur.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

		// Creating a Goal
		Goal goal = new Goal();
		goal.setType(goalRequestDTO.getType());
		goal.setFrequency(goalRequestDTO.getFrequency());
		goal.setTarget(goalRequestDTO.getTarget());
		goal.setCurrentProgress(0); // Initial progress is 0
		goal.setStartDate(goalRequestDTO.getStartDate());
		goal.setEndDate(goalRequestDTO.getEndDate());
		goal.setStatus(GoalStatus.ONGOING); // Set default status
		goal.setMetric(goalRequestDTO.getMetric());
		goal.setUser(user);
		
		    
		return gr.save(goal);
	}
	
	@Override
	public Goal createSleepGoal(Long userId, GoalRequestDTO goalRequestDTO) {
		// Checking if a User exists
		Users user = ur.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

		// Creating a Goal
		Goal goal = new Goal();
		goal.setType(goalRequestDTO.getType());
		goal.setFrequency(goalRequestDTO.getFrequency());
		goal.setTarget(goalRequestDTO.getTarget());
		goal.setCurrentProgress(0); // Initial progress is 0
		goal.setStartDate(goalRequestDTO.getStartDate());
		goal.setEndDate(goalRequestDTO.getEndDate());
		goal.setStatus(GoalStatus.ONGOING); // Set default status
		goal.setMetric(goalRequestDTO.getMetric());
		goal.setUser(user);
		    
		return gr.save(goal);
	}
	
	@Override
	public Goal createDietGoal(Long userId, GoalRequestDTO goalRequestDTO) {
	    // Checking if a User exists
	    Users user = ur.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (goalRequestDTO.getMetric() != GoalMetric.CALORIES_CONSUMED) {
	        throw new IllegalArgumentException("Goal metric must be DIET for this method.");
	    }

	    double bmr = us.calculateBMR(user);
	    double activityMultiplier = us.getActivityMultiplier(user);
	    double tdee = bmr * activityMultiplier;

	    // Adjust TDEE based on weight goal
	    double calorieTarget;
	    if (goalRequestDTO.getWeightGoal() != null) {
	        switch (goalRequestDTO.getWeightGoal()) {
	            case LOOSE_WEIGHT :
	            	calorieTarget = tdee * 0.8; // 20% calorie deficit
	                break;
	               
	            case GAIN_WEIGHT :
	                calorieTarget = tdee * 1.2; // 20% surplus
	                break;
	                
	            default:
	            	calorieTarget = tdee; // Maintain
	        }
	    } else {
	        calorieTarget = tdee; // Default to maintenance
	    }

	    Goal goal = new Goal();
	    goal.setUser(user);
	    goal.setType(goalRequestDTO.getType());
	    goal.setFrequency(goalRequestDTO.getFrequency());
	    goal.setTarget(calorieTarget); // Set calculated calories as target
	    goal.setCurrentProgress(0.0);
	    goal.setStartDate(goalRequestDTO.getStartDate());
	    goal.setEndDate(goalRequestDTO.getEndDate());
	    goal.setStatus(GoalStatus.ONGOING);
	    goal.setMetric(GoalMetric.CALORIES_CONSUMED);
	    goal.setWeightGoal(goalRequestDTO.getWeightGoal());

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

	// New method to update the goal progress and status based on progress
	@Override
	public void updateGoalProgress(Goal goal, Double progress) {
	    if (goal.getMetric() == null) {
	        throw new IllegalArgumentException("Goal metric not specified.");
	    }

	    // Update the progress based on the metric
	    switch (goal.getMetric()) {
	       
	    case DURATION:              // For sleep
	        case CALORIES_BURNED: 
	        	goal.setCurrentProgress(goal.getCurrentProgress() + progress);
	            break;				// For exercise calorie-burning goals
	       
	        case CALORIES_CONSUMED:     			 // For diet calorie-intake goals
	           updateDietGoal(goal,goal.getUser(),goal.getStartDate());
	           break;
	        
	        default:
	            //throw new IllegalArgumentException("Unsupported metric type for goal.");
	            throw new IllegalArgumentException("Unsupported metric type: " + goal.getMetric());
	    
	    }

	    // Check if the goal is completed
	    if (goal.getCurrentProgress() >= goal.getTarget()) {
	        goal.setStatus(GoalStatus.COMPLETED);
	    }

	    // Mark the goal as FAILED if the deadline has passed without completion
	    if (goal.getEndDate() != null && goal.getEndDate().isBefore(LocalDate.now())) {
	        if (goal.getCurrentProgress() < goal.getTarget()) {
	            goal.setStatus(GoalStatus.FAILED);
	        }
	    }

	    // Save the goal after updating its status
	    gr.save(goal);
	}
	
	private Goal updateDietGoal(Goal goal,Users user,LocalDate date) { //we will only check daily goals
		
		 List<MealLog> mealsToday = mlr.findByUserIdAndDate(user.getId(), date);
		 System.out.println(mealsToday);
		    double totalCaloriesToday = 0;
		    for (MealLog meal : mealsToday) {
		        System.out.println("Meal: " + meal.getId() + ", Calories: " + meal.getTotalCalories()); //4check
		        totalCaloriesToday += meal.getTotalCalories();
		    }
		    System.out.println(totalCaloriesToday);
		    goal.setCurrentProgress(totalCaloriesToday);
			return goal;
	}


}
		



