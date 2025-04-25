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
@Table(name = "mentalhealthalert") 

public class MentalHealthAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String message;
    private String counsellorName;
    private String counsellorPhone;
    
 // --- Constructors ---
    
    public MentalHealthAlert() {}

    public MentalHealthAlert(Long id, LocalDate date, String message, String counsellorName, String counsellorPhone,
			Users user) {
		super();
		this.id = id;
		this.date = date;
		this.message = message;
		this.counsellorName = counsellorName;
		this.counsellorPhone = counsellorPhone;
		this.user = user;
	}

	// --- toString ---

	@Override
	public String toString() {
		return "MentalHealthAlert [id=" + id + ", date=" + date + ", message=" + message + ", counsellorName="
				+ counsellorName + ", counsellorPhone=" + counsellorPhone + "]";
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCounsellorName() {
		return counsellorName;
	}

	public void setCounsellorName(String counsellorName) {
		this.counsellorName = counsellorName;
	}

	public String getCounsellorPhone() {
		return counsellorPhone;
	}

	public void setCounsellorPhone(String counsellorPhone) {
		this.counsellorPhone = counsellorPhone;
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
