package com.reflecta.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reflecta.enums.Frequency;
import com.reflecta.enums.GoalMetric;
import com.reflecta.enums.GoalType;
import com.reflecta.enums.WeightGoal;

public class GoalRequestDTO {
    
    private GoalType type; // Type of goal (SLEEP, EXERCISE, DIET)
    private Frequency frequency; // Frequency (DAILY, WEEKLY, etc.)
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double target;
    // General target for the goal (could be hours, calories, etc.)
    
    private LocalDate startDate; // Start date of the goal
    private LocalDate endDate; // End date of the goal
    private GoalMetric metric;
    private WeightGoal weightGoal;

	// Getters and Setters
    public GoalMetric getMetric() {
		return metric;
	}

	public void setMetric(GoalMetric metric) {
		this.metric = metric;
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

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
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

	public WeightGoal getWeightGoal() {
		return weightGoal;
	}

	public void setWeightGoal(WeightGoal weightGoal) {
		this.weightGoal = weightGoal;
	}
	
}

