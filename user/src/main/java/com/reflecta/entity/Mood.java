package com.reflecta.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reflecta.enums.MoodStatus;

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
@Table(name = "mood") 

	public class Mood {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private LocalDate date;
	    @Enumerated(EnumType.STRING)
	    private MoodStatus moodStatus;
	    private int stressLevel;
	    
	 // --- Constructors ---
	    
	    public Mood() {}

	    public Mood(Long id, LocalDate date, MoodStatus moodStatus, int stressLevel, Users user) {
			super();
			this.id = id;
			this.date = date;
			this.moodStatus = moodStatus;
			//this.stressLevel = stressLevel;
			this.user = user;
		}

		// --- toString ---

		@Override
		public String toString() {
//			return "Mood [id=" + id + ", date=" + date + ", moodStatus=" + moodStatus + ", stressLevel=" + stressLevel
//					+ "]";
			return "Mood [id=" + id + ", date=" + date + ", moodStatus=" + moodStatus + "]";
			
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

		public MoodStatus getMoodStatus() {
			return moodStatus;
		}

		public void setMoodStatus(MoodStatus moodStatus) {
			this.moodStatus = moodStatus;
		}

	public int getStressLevel() {
		return stressLevel;
	}

	public void setStressLevel(int stressLevel) {
		this.stressLevel = stressLevel;
	}

		public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}
		 
		
		@ManyToOne(optional = false)
		@JoinColumn(name = "user_id")
		@JsonIgnore
		private Users user;
		
		
//		 //added for creating a relation to implement Goal
//		 @ManyToOne
//		 @JoinColumn(name = "goal_id")
//		 private Goal goal;
}



