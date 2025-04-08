package com.reflecta.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;



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
    
    public Sleep() {}

    public Sleep(Long id, LocalDate date, LocalTime sleepStartTime, LocalTime sleepEndTime, double durationHours,
			Users user) {
		super();
		this.id = id;
		this.date = date;
		this.sleepStartTime = sleepStartTime;
		this.sleepEndTime = sleepEndTime;
		this.durationHours = durationHours;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Sleep [id=" + id + ", date=" + date + ", sleepStartTime=" + sleepStartTime + ", sleepEndTime="
				+ sleepEndTime + ", durationHours=" + durationHours + ", user=" + user + ", getId()=" + getId()
				+ ", getDate()=" + getDate() + ", getSleepStartTime()=" + getSleepStartTime() + ", getSleepEndTime()="
				+ getSleepEndTime() + ", getDurationHours()=" + getDurationHours() + ", getUser()=" + getUser()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
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

	@ManyToOne
    private Users user;
}
