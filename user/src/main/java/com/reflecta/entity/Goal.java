package com.reflecta.entity;
import java.time.LocalDate;

import com.reflecta.enums.goal.Frequency;
import com.reflecta.enums.goal.GoalType;

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
@Table(name = "goal") 
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GoalType type;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    private double targetHours;
    private double currentProgress;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

 // --- Getters & Setters --
    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public GoalType getType() {
		return type;
	}


	public void setType(GoalType type) {
		this.type = type;
	}


	public Frequency getFrequency() {
		return frequency;
	}


	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}


	public double getTargetHours() {
		return targetHours;
	}


	public void setTargetHours(double targetHours) {
		this.targetHours = targetHours;
	}


	public double getCurrentProgress() {
		return currentProgress;
	}


	public void setCurrentProgress(double currentProgress) {
		this.currentProgress = currentProgress;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	
	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	// --- toString ---

	@Override
	public String toString() {
		return "Goal [id=" + id + ", type=" + type + ", frequency=" + frequency + ", targetHours=" + targetHours
				+ ", currentProgress=" + currentProgress + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + "]";
		//, user=" + user + "]";
	}
	
	// --- Constructors ---
	
	public Goal(Long id, GoalType type, Frequency frequency, double targetHours, double currentProgress,
			LocalDate startDate, LocalDate endDate, String status, Users user) {
		super();
		this.id = id;
		this.type = type;
		this.frequency = frequency;
		this.targetHours = targetHours;
		this.currentProgress = currentProgress;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.user = user;
	}
	
	public Goal() {}


	 @ManyToOne(optional = false)
	 @JoinColumn(name = "user_id")
	    private Users user;
}


