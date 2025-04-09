package com.reflecta.entity;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
//@Data
@Table(name = "dailysummary") 

public class DailySummary {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private LocalDate date;
	    private int totalSteps;
	    private double totalCaloriesBurned;
	    private double totalCaloriesConsumed;
	    private double sleepHours;
	    private String moodStatus;

	    
	    public DailySummary() {}
	    @Override
		public String toString() {
			return "DailySummary [id=" + id + ", date=" + date + ", totalSteps=" + totalSteps + ", totalCaloriesBurned="
					+ totalCaloriesBurned + ", totalCaloriesConsumed=" + totalCaloriesConsumed + ", sleepHours="
					+ sleepHours + ", moodStatus=" + moodStatus + ", user=" + user + ", getId()=" + getId()
					+ ", getDate()=" + getDate() + ", getTotalSteps()=" + getTotalSteps()
					+ ", getTotalCaloriesBurned()=" + getTotalCaloriesBurned() + ", getTotalCaloriesConsumed()="
					+ getTotalCaloriesConsumed() + ", getSleepHours()=" + getSleepHours() + ", getMoodStatus()="
					+ getMoodStatus() + ", getUser()=" + getUser() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
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

		public int getTotalSteps() {
			return totalSteps;
		}

		public void setTotalSteps(int totalSteps) {
			this.totalSteps = totalSteps;
		}

		public double getTotalCaloriesBurned() {
			return totalCaloriesBurned;
		}

		public void setTotalCaloriesBurned(double totalCaloriesBurned) {
			this.totalCaloriesBurned = totalCaloriesBurned;
		}

		public double getTotalCaloriesConsumed() {
			return totalCaloriesConsumed;
		}

		public void setTotalCaloriesConsumed(double totalCaloriesConsumed) {
			this.totalCaloriesConsumed = totalCaloriesConsumed;
		}

		public double getSleepHours() {
			return sleepHours;
		}

		public void setSleepHours(double sleepHours) {
			this.sleepHours = sleepHours;
		}

		public String getMoodStatus() {
			return moodStatus;
		}

		public void setMoodStatus(String moodStatus) {
			this.moodStatus = moodStatus;
		}

		public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

		public DailySummary(Long id, LocalDate date, int totalSteps, double totalCaloriesBurned,
				double totalCaloriesConsumed, double sleepHours, String moodStatus, Users user) {
			super();
			this.id = id;
			this.date = date;
			this.totalSteps = totalSteps;
			this.totalCaloriesBurned = totalCaloriesBurned;
			this.totalCaloriesConsumed = totalCaloriesConsumed;
			this.sleepHours = sleepHours;
			this.moodStatus = moodStatus;
			this.user = user;
		}

		@ManyToOne
	    private Users user;
	}



