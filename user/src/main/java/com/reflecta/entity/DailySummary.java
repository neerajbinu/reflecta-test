package com.reflecta.entity;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
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

	    @ManyToOne
	    private Users user;
	}



