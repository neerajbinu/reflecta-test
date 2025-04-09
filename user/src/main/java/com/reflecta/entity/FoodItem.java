package com.reflecta.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
//@Data
@Table(name = "fooditem") 

public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double caloriesPerServing;
    private double carbsPerServing;
    private double proteinPerServing;
    private double fatPerServing;
    
 // --- Constructors ---
    
    public FoodItem( ) {}
    
	public FoodItem(Long id, String name, double caloriesPerServing, double carbsPerServing, double proteinPerServing,
			double fatPerServing) {
		super();
		this.id = id;
		this.name = name;
		this.caloriesPerServing = caloriesPerServing;
		this.carbsPerServing = carbsPerServing;
		this.proteinPerServing = proteinPerServing;
		this.fatPerServing = fatPerServing;
	}
	
	// --- toString ---

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", caloriesPerServing=" + caloriesPerServing
				+ ", carbsPerServing=" + carbsPerServing + ", proteinPerServing=" + proteinPerServing
				+ ", fatPerServing=" + fatPerServing + "]";
		
//		, getId()=" + getId() + ", getName()=" + getName()
//				+ ", getCaloriesPerServing()=" + getCaloriesPerServing() + ", getCarbsPerServing()="
//				+ getCarbsPerServing() + ", getProteinPerServing()=" + getProteinPerServing() + ", getFatPerServing()="
//				+ getFatPerServing() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
//				+ super.toString() + "]";
	}
	
	// --- Getters & Setters --
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCaloriesPerServing() {
		return caloriesPerServing;
	}
	public void setCaloriesPerServing(double caloriesPerServing) {
		this.caloriesPerServing = caloriesPerServing;
	}
	public double getCarbsPerServing() {
		return carbsPerServing;
	}
	public void setCarbsPerServing(double carbsPerServing) {
		this.carbsPerServing = carbsPerServing;
	}
	public double getProteinPerServing() {
		return proteinPerServing;
	}
	public void setProteinPerServing(double proteinPerServing) {
		this.proteinPerServing = proteinPerServing;
	}
	public double getFatPerServing() {
		return fatPerServing;
	}
	public void setFatPerServing(double fatPerServing) {
		this.fatPerServing = fatPerServing;
	}
}

