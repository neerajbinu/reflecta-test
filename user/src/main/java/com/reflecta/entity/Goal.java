package com.reflecta.entity;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reflecta.enums.Frequency;
import com.reflecta.enums.GoalMetric;
import com.reflecta.enums.GoalStatus;
import com.reflecta.enums.GoalType;
import com.reflecta.enums.WeightGoal;

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
@Table(name = "goal") 
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GoalType type;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    
    @Enumerated(EnumType.STRING)
    private GoalStatus status;
    
    @Enumerated(EnumType.STRING)
    private GoalMetric metric;
   
    @Enumerated(EnumType.STRING)
    private WeightGoal weightGoal;
    
	private double target; //unified target for tracking all of the entities
    private double currentProgress;
    private LocalDate startDate;
    private LocalDate endDate;
   

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
	
	public GoalStatus getStatus()
	{
		return status;
	}
	
	public void setStatus(GoalStatus status) {
			this.status = status;
		}
	  
	
	public GoalMetric getMetric() {
		return metric;
	}


	public void setMetric(GoalMetric metric) {
		this.metric = metric;
	}
	


	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public WeightGoal getWeightGoal() {
		return weightGoal;
	}


	public void setWeightGoal(WeightGoal weightGoal) {
		this.weightGoal = weightGoal;
	}

	// --- toString ---

	@Override
	public String toString() {
		return "Goal [id=" + id + ", type=" + type + ", frequency=" + frequency + ", target=" + target
				+ ", currentProgress=" + currentProgress + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + "]";
		//, user=" + user + "]";
	}
	
	// --- Constructors ---
	
	public Goal(Long id, GoalType type, Frequency frequency, double target, double currentProgress,
			LocalDate startDate, LocalDate endDate, GoalStatus status, GoalMetric metric, WeightGoal weightGoal,Users user) {
		super();
		this.id = id;
		this.type = type;
		this.frequency = frequency;
		this.target=target;
		this.currentProgress = currentProgress;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.metric=metric;
		this.weightGoal=weightGoal;
		this.user = user;
	}
	
	public Goal() {}


	 public double getTarget() {
		return target;
	}


	public void setTarget(double target) {
		this.target = target;
	}


	@ManyToOne(optional = false)
	 @JoinColumn(name = "user_id")
	 @JsonIgnore
	    private Users user; 
	 
}


