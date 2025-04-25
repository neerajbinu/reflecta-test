package com.reflecta.dto;

import java.time.LocalDate;

import com.reflecta.enums.Frequency;
import com.reflecta.enums.GoalType;

public class GoalRequestDTO {
    private GoalType type;
    private Frequency frequency;
    private double targetHours;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId; // To identify the user who is creating the goal

    // Getters and Setters
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

