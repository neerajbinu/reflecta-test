package com.reflecta.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
//@Data
@Table(name = "excercisedata") 

public class ExerciseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String exerciseType;
    private double durationMinutes;
    private double caloriesBurned;
    
 // --- Constructors ---

    public ExerciseData() {}
    public ExerciseData(Long id, LocalDate date, String exerciseType, double durationMinutes, double caloriesBurned,
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

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
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

	 @ManyToOne(optional = false)
	 @JoinColumn(name = "user_id")
	    private Users user;
}
