package com.reflecta.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reflecta.enums.ExerciseType;

@Entity
//@Data
@Table(name = "excercisedata") 

public class ExerciseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private ExerciseType exerciseType;
    private double durationMinutes;
    private double caloriesBurned;
    
 // --- Constructors ---

    public ExerciseData() {}
    public ExerciseData(Long id, LocalDate date, ExerciseType exerciseType, double durationMinutes, double caloriesBurned,
			Users user) {
		super();
		this.id = id;
		this.date = date;
		this.exerciseType = exerciseType;
		this.durationMinutes = durationMinutes;
		this.caloriesBurned = caloriesBurned;
		this.user = user;
	}

	// --- toString ---

	@Override
	public String toString() {
		return "ExerciseData [id=" + id + ", date=" + date + ", exerciseType=" + exerciseType + ", durationMinutes="
		+ durationMinutes + ", caloriesBurned=" + caloriesBurned + "]";
		
				//, user=" + user + ", getId()=" + getId()
//				+ ", getDate()=" + getDate() + ", getExerciseType()=" + getExerciseType() + ", getDurationMinutes()="
//				+ getDurationMinutes() + ", getCaloriesBurned()=" + getCaloriesBurned() + ", getUser()=" + getUser()
//				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
//				+ "]";
	}
	
	// --- Getters & Setters --

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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }


	 @ManyToOne(optional = false)
	 @JoinColumn(name = "user_id")
	 @JsonIgnore
	    private Users user;
	 
	 //added for creating a relation to implement Goal
	 @ManyToOne
	 @JoinColumn(name = "goal_id")
	 private Goal goal;

	 //For ENUM to run, delete the ExerciseData table(data is stored as ordinal numbers) and try again...However it won't be a problem as
	 //it will be fetched as a String
//	 @Enumerated(EnumType.STRING)
//	 private ExerciseType exerciseType;
}
