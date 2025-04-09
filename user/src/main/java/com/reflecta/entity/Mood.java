package com.reflecta.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
//@Data
@Table(name = "mood") 

	public class Mood {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private LocalDate date;
	    private String moodStatus;
	    private int stressLevel;
	    
	 // --- Constructors ---
	    
	    public Mood() {}

	    public Mood(Long id, LocalDate date, String moodStatus, int stressLevel, Users user) {
			super();
			this.id = id;
			this.date = date;
			this.moodStatus = moodStatus;
			this.stressLevel = stressLevel;
			this.user = user;
		}

		// --- toString ---

		@Override
		public String toString() {
			return "Mood [id=" + id + ", date=" + date + ", moodStatus=" + moodStatus + ", stressLevel=" + stressLevel
					+ "]";
			
//					, user=" + user + ", getId()=" + getId() + ", getDate()=" + getDate() + ", getMoodStatus()="
//					+ getMoodStatus() + ", getStressLevel()=" + getStressLevel() + ", getUser()=" + getUser()
//					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
//					+ "]";
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

		public String getMoodStatus() {
			return moodStatus;
		}

		public void setMoodStatus(String moodStatus) {
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
		    private Users user;
}



