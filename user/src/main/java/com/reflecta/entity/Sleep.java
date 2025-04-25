package com.reflecta.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reflecta.enums.SleepQuality;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
//@Data
@Table(name = "sleep") 

public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime sleepStartTime;
    private LocalTime sleepEndTime;
    private double durationHours;
    
    @Enumerated(EnumType.STRING)
    private SleepQuality sleepQuality;
    
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

	public LocalTime getSleepStartTime() {
		return sleepStartTime;
	}

	public void setSleepStartTime(LocalTime sleepStartTime) {
		this.sleepStartTime = sleepStartTime;
	}

	public LocalTime getSleepEndTime() {
		return sleepEndTime;
	}

	public void setSleepEndTime(LocalTime sleepEndTime) {
		this.sleepEndTime = sleepEndTime;
	}

	public double getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(double durationHours) {
		this.durationHours = durationHours;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
    
    public SleepQuality getSleepQuality() {
		return sleepQuality;
	}

	public void setSleepQuality(SleepQuality sleepQuality) {
		this.sleepQuality = sleepQuality;
	}
	
	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	// --- Constructors ---
	

	public Sleep() {}

	public Sleep(Long id, LocalDate date, LocalTime sleepStartTime, LocalTime sleepEndTime, double durationHours,
			SleepQuality sleepQuality, Users user,Goal goal) {
		super();
		this.id = id;
		this.date = date;
		this.sleepStartTime = sleepStartTime;
		this.sleepEndTime = sleepEndTime;
		this.durationHours = durationHours;
		this.sleepQuality = sleepQuality;
		this.user = user;
		this.goal=goal;
	}

	// --- toString ---

	@Override
	public String toString() {
		return "Sleep [id=" + id + ", date=" + date + ", sleepStartTime=" + sleepStartTime + ", sleepEndTime="
				+ sleepEndTime + ", durationHours=" + durationHours + "]";
		
//		, user=" + user + ", getId()=" + getId()
//				+ ", getDate()=" + getDate() + ", getSleepStartTime()=" + getSleepStartTime() + ", getSleepEndTime()="
//				+ getSleepEndTime() + ", getDurationHours()=" + getDurationHours() + ", getUser()=" + getUser()
//				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
//				+ "]";
	}




	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
	@JsonIgnore
    private Users user;
	
	 //added for creating a relation to implement Goal
	 @ManyToOne
	 @JoinColumn(name = "goal_id")
	 @JsonIgnore
	 private Goal goal;
}
