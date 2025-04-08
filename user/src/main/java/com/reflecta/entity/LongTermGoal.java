package com.reflecta.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

	@Entity
	@Data
	public class LongTermGoal {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String goalType;
	    private String goalCategory;
	    private double targetValue;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private String status;
	    private String description;

	    @ManyToOne
	    private Users user;
	}


