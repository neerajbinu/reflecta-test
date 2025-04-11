package com.reflecta.dto;

import java.time.LocalDate;

import com.reflecta.enums.ExerciseType;

public class ExerciseDataResponseDTO {
	
	    private Long id;
	    private LocalDate date;
	    private ExerciseType exerciseType;
	    private double durationMinutes;
	    private double caloriesBurned;
	    private String message; 
	    private Long goalId;// Optional - success/info message
	    private String username;

	    // Constructors
	    public ExerciseDataResponseDTO() {}

	    public ExerciseDataResponseDTO(Long id, LocalDate date, ExerciseType exerciseType, double durationMinutes, double caloriesBurned, String message) {
	        this.id = id;
	        this.date = date;
	        this.exerciseType = exerciseType;
	        this.durationMinutes = durationMinutes;
	        this.caloriesBurned = caloriesBurned;
	        this.message = message;
	    }

	    // Getters & Setters
	    public Long getGoalId() {
	        return goalId;
	    }

	    public void setGoalId(Long goalId) {
	        this.goalId = goalId;
	    }
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
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

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
	    public String getUserName() {
	        return username;
	    }

	    public void setUserName(String username) {
	        this.username = username;
	    }
	
}
