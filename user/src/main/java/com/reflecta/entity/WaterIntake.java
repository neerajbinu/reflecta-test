package com.reflecta.entity;


import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "water_intake")
public class WaterIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    JsonIgnore for preventing loops in Postman
    @JsonIgnore 
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private Users user;

    private LocalDate date;

    private Integer totalMl;

    private Integer goalMl = 2000; // Default daily goal 
    


    // --- Constructors ---

    public WaterIntake() {}

    public WaterIntake(Users user, LocalDate date, Integer totalMl, Integer goalMl) {
        this.user = user;
        this.date = date;
        this.totalMl = totalMl;
        this.goalMl = goalMl;
    }

    // --- toString ---

    @Override
	public String toString() {
		return "WaterIntake [id=" + id + ", date=" + date + ", totalMl=" + totalMl + ", goalMl=" + goalMl + "]";
		
//		, getId()="
//				+ getId() + ", getDate()=" + getDate() + ", getTotalMl()=" + getTotalMl() + ", getGoalMl()="
//				+ getGoalMl() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
//				+ super.toString() + "]";
	}
    
     // --- Getters & Setters --
	public Long getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTotalMl() {
        return totalMl;
    }

    public void setTotalMl(Integer totalMl) {
        this.totalMl = totalMl;
    }

    public Integer getGoalMl() {
        return goalMl;
    }

    public void setGoalMl(Integer goalMl) {
        this.goalMl = goalMl;
    }
}
