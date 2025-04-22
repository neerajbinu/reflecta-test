package com.reflecta.dto;

import java.time.LocalTime;

public class SleepSessionDTO {
    private Long id;
    private LocalTime sleepStartTime;
    private LocalTime sleepEndTime;
    private double durationHours;
    private int disturbances;

    public SleepSessionDTO(Long id, LocalTime sleepStartTime, LocalTime sleepEndTime, double durationHours, int disturbances) {
        this.id = id;
        this.sleepStartTime = sleepStartTime;
        this.sleepEndTime = sleepEndTime;
        this.durationHours = durationHours;
        this.disturbances = disturbances;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getDisturbances() {
		return disturbances;
	}

	public void setDisturbances(int disturbances) {
		this.disturbances = disturbances;
	}

   
}

