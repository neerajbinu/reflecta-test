package com.reflecta.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.reflecta.entity.Goal;
import com.reflecta.enums.Frequency;
import com.reflecta.enums.GoalStatus;
import com.reflecta.repository.GoalRepository;

@Service
	public class GoalSchedulerService {

	    private final GoalRepository goalRepository;

	    @Autowired
	    public GoalSchedulerService(GoalRepository goalRepository) {
	        this.goalRepository = goalRepository;
	    }

	   // @Scheduled(cron = "0 0 0 * * ?") // Every day at midnight
	    @Scheduled(cron = "0 58 12 * * ?") // Runs at 12:50 PM daily

	    public void checkDailyGoals() {
	        LocalDate today = LocalDate.now(); // Check for yesterday's goals
	        List<Goal> dailyGoals = goalRepository.findAllByFrequencyAndStatus(Frequency.DAILY, GoalStatus.ONGOING);

	        for (Goal goal : dailyGoals) {
	            if (goal.getStartDate().isAfter(today) || goal.getEndDate().isBefore(today)) continue;

	            boolean goalMet = switch (goal.getType()) {
	                case SLEEP -> goal.getCurrentProgress() >= goal.getTargetHours();
	                case EXERCISE -> goal.getCurrentProgress() >= goal.getTargetHours();
	                case DIET -> goal.getCurrentProgress() >= goal.getTargetHours();
	                default -> true;
	            };

	            if (!goalMet) {
	                goal.setStatus(GoalStatus.FAILED);
	                goalRepository.save(goal);
	            }
	        }
	    }

	    
	    
//	    Commented out to only check the Daily Goals
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

	}


