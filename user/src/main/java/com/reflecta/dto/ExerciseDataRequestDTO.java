package com.reflecta.dto;

import java.time.LocalDate;

import com.reflecta.enums.ExerciseType;

public class ExerciseDataRequestDTO {
	    private Long userId;
	    private ExerciseType exerciseType;
	    private double durationMinutes;
	    private double caloriesBurned;
	    private Long goalId; // Optional
	    private LocalDate date;

	    // Getters & Setters
	    
	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public ExerciseType getExerciseType() {
	        return exerciseType;
	    }

	    public void setExerciseType(ExerciseType exerciseType) {
	        this.exerciseType = exerciseType;
	    }

	    public double getDurationMinutes() {
	        return durationMinutes;
	    }

	    public void setDurationMinutes(double durationMinutes) {
	        this.durationMinutes = durationMinutes;
	    }

	    public double getCaloriesBurned() {
	        return caloriesBurned;
	    }

	    public void setCaloriesBurned(double caloriesBurned) {
	        this.caloriesBurned = caloriesBurned;
	    }

	    public Long getGoalId() {
	        return goalId;
	    }

	    public void setGoalId(Long goalId) {
	        this.goalId = goalId;
	    }
	    
	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }
	}

