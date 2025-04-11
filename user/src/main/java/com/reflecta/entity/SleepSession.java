package com.reflecta.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sleep_sessions")
public class SleepSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime sleepStartTime;
    private LocalTime sleepEndTime;
    private double durationHours;
    private int disturbances;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "sleep_id", nullable = false)
    private Sleep sleep; // reference to the main Sleep for aggregation

    public SleepSession() {}

    public SleepSession(LocalTime sleepStartTime, LocalTime sleepEndTime, double durationHours, Users user, Sleep sleep,int disturbances) {
        this.sleepStartTime = sleepStartTime;
        this.sleepEndTime = sleepEndTime;
        this.durationHours = durationHours;
        this.user = user;
        this.sleep = sleep;
        this.disturbances=disturbances;
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

	public Sleep getSleep() {
		return sleep;
	}

	public void setSleep(Sleep sleep) {
		this.sleep = sleep;
	}
	
	public Users getUser() {
	    return this.user;
	}

	
	public void setUser(Users user) {
		this.user=user;
	}

	public int getDisturbances() {
		return disturbances;
	}

	public void setDisturbances(int disturbances) {
		this.disturbances = disturbances;
	}

	@Override
	public String toString() {
		return "SleepSession [id=" + id + ", sleepStartTime=" + sleepStartTime + ", sleepEndTime=" + sleepEndTime
				+ ", durationHours=" + durationHours + ", disturbances=" + disturbances + ", user=" + user + ", sleep="
				+ sleep + "]";
	}
	
	

    // Getters and Setters...
    
}
