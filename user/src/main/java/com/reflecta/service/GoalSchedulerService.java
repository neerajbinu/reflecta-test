package com.reflecta.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.reflecta.entity.Goal;
import com.reflecta.entity.Users;
import com.reflecta.enums.Frequency;
import com.reflecta.enums.GoalStatus;
import com.reflecta.enums.GoalType;
import com.reflecta.repository.GoalRepository;
import com.reflecta.repository.UsersRepository;

@Service
	public class GoalSchedulerService {
		
		@Autowired
	    private  GoalRepository goalRepository;
		
		@Autowired
		private GoalService gs;
		
		@Autowired
		private UsersRepository us; 

		@Autowired
		private MoodService ms;
		
		@Autowired
		private SleepService ss;
		
	    @Scheduled(cron = "0 0/2 * * * ?") // every 2 minutes
	    public void updateDietGoalsScheduler() {
	        LocalDate today = LocalDate.now();
	        List<Goal> dietGoals = goalRepository.findAllByTypeAndStatus(GoalType.DIET, GoalStatus.ONGOING);

	        for (Goal goal : dietGoals) {
	            if (!goal.getStartDate().isAfter(today) && !goal.getEndDate().isBefore(today)) {
	                gs.updateGoalProgress(goal, 0.0); // internally calls updateDietGoal
	            }
	        }
	    }

	  
	    @Scheduled(cron = "0 41 12 * * ?") // Runs at 12:41 PM daily
	    public void checkDailyGoals() {
	        LocalDate today = LocalDate.now();
	        List<Goal> dailyGoals = goalRepository.findAllByFrequencyAndStatus(Frequency.DAILY, GoalStatus.ONGOING);

	        for (Goal goal : dailyGoals) {
	            if (goal.getStartDate().isAfter(today) || goal.getEndDate().isBefore(today)) continue;

	            boolean goalMet = switch (goal.getType()) {
	                case SLEEP, EXERCISE, DIET -> goal.getCurrentProgress() >= goal.getTarget();
	                default -> true;
	            };

	            if (!goalMet) {
	                goal.setStatus(GoalStatus.FAILED);
	                goalRepository.save(goal);
	                System.out.println("Goal ID " + goal.getId() + " failed.");
	            }

	            
	            // Optional reset(if you want to reset the progress for the next day)
	            // goal.setCurrentProgress(0.0);
	            // goalRepository.save(goal);
	        }
	    }

	        @Scheduled(cron = "0 0 0 * * SUN") // Every Sunday at midnight
	        public void evaluateWeeklyMentalHealth() {
	            List<Users> allUsers = us.findAll();

	            for (Users user : allUsers) {
	                Long userId = user.getId();
	                double moodAvg = ms.calculateWeeklyAverageMoodScore(userId);
	                double sleepAvg = ss.calculateWeeklySleepQualityScore(userId); // you'd implement this
	             
	                // --- Evaluation logic ---
	    		    if (moodAvg > 3.0 && sleepAvg > 3.0) {
	    		    	System.out.println("User " + userId + ": Your mental wellness might be deteriorating. Consider consulting a counsellor.");
	    		    } else {
	    		    	System.out.println("User " + userId + ": Your mental wellness appears stable.");
	    		    }
	    		    
	            }
	        }
	    }

	    
	    
//	    Commented out to only check the Daily Goals...The following 
//	    @Scheduled(cron = "0 0 0 * * MON") // Every Monday
//	    public void checkWeeklyGoals() {
//	        LocalDate lastWeek = LocalDate.now().minusWeeks(1);
//	        List<Goal> weeklyGoals = goalRepository.findAllByFrequencyAndStatus(Frequency.WEEKLY, GoalStatus.ONGOING);
//
//	        for (Goal goal : weeklyGoals) {
//	            if (goal.getStartDate().isAfter(lastWeek) || goal.getEndDate().isBefore(lastWeek)) continue;
//
//	            boolean goalMet = goal.getCurrentProgress() >= goal.getTargetHours();
//	            if (!goalMet) {
//	                goal.setStatus(GoalStatus.FAILED);
//	                goalRepository.save(goal);
//	            }
//	        }
//	    }
//
//	    @Scheduled(cron = "0 0 0 1 * ?") // 1st day of every month
//	    public void checkMonthlyGoals() {
//	        LocalDate lastMonth = LocalDate.now().minusMonths(1);
//	        List<Goal> monthlyGoals = goalRepository.findAllByFrequencyAndStatus(Frequency.LONG_TERM, GoalStatus.ONGOING);
//
//	        for (Goal goal : monthlyGoals) {
//	            if (goal.getStartDate().isAfter(lastMonth) || goal.getEndDate().isBefore(lastMonth)) continue;
//
//	            boolean goalMet = goal.getCurrentProgress() >= goal.getTargetHours();
//	            if (!goalMet) {
//	                goal.setStatus(GoalStatus.FAILED);
//	                goalRepository.save(goal);
//	            }
//	        }
//	    }

	


