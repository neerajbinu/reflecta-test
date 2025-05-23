package com.reflecta.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//@Data
@Table(name = "journalentry") 

	public class JournalEntry {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private LocalDate date;
	    private String title;
	    private String content;
	    
	 // --- Constructors ---
	    
	    public JournalEntry() {}

	    public JournalEntry(Long id, LocalDate date, String title, String content, Users user) {
			super();
			this.id = id;
			this.date = date;
			this.title = title;
			this.content = content;
			this.user = user;
		}
	    
		// --- toString ---

		@Override
		public String toString() {
			return "JournalEntry [id=" + id + ", date=" + date + ", title=" + title + ", content=" + content + "]";

					//					, user="
//					+ user + ", getId()=" + getId() + ", getDate()=" + getDate() + ", getTitle()=" + getTitle()
//					+ ", getContent()=" + getContent() + ", getUser()=" + getUser() + ", getClass()=" + getClass()
//					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
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

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
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
	

}


