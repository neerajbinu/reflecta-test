//MealLog.java
package com.reflecta.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reflecta.enums.GoalType;
import com.reflecta.enums.mealLog.MealType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
//@Data
@Table(name = "meal_log") 

public class MealLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double servings;
    private double totalCalories;
    private double totalCarbs;
    private double totalProtein;
    private double totalFat;
    private double totalFibre;
    private double totalSugar;

    

	@Enumerated(EnumType.STRING)
    private MealType type;

    
 // --- Constructors ---
    
    public MealLog() {}
    
    public MealLog(Long id, LocalDate date, double servings, double totalCalories, double totalCarbs, double totalProtein,
			double totalFat, double totalFibre, double totalSugar, MealType type, FoodItem foodItem, Users user) {
		super();
		this.id = id;
		this.date = date;
		this.servings = servings;
		this.totalCalories = totalCalories;
		this.totalCarbs = totalCarbs;
		this.totalProtein = totalProtein;
		this.totalFat = totalFat;
		this.totalFibre = totalFibre;
		this.totalSugar = totalSugar;
		this.type = type;
		this.foodItem = foodItem;
		this.user = user;
	}
    
	// --- toString ---

	@Override
	public String toString() {
		return "Diet [id=" + id + ", date=" + date + ", servings=" + servings + ", totalCalories=" + totalCalories
				+ ", totalCarbs=" + totalCarbs + ", totalProtein=" + totalProtein + ", totalFat=" + totalFat
				+ ", totalFibre=" + totalFibre + ", totalSugar=" + totalSugar + ", type=" + type + ", foodItem="
				+ foodItem + "]";
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

	public MealType getMealType() {
		return type;
	}

	public void setMealType(MealType type) {
		this.type = type;
	}

	public double getServings() {
		return servings;
	}

	public void setServings(double servings) {
		this.servings = servings;
	}

	public double getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}

	public double getTotalCarbs() {
		return totalCarbs;
	}

	public void setTotalCarbs(double totalCarbs) {
		this.totalCarbs = totalCarbs;
	}

	public double getTotalProtein() {
		return totalProtein;
	}

	public void setTotalProtein(double totalProtein) {
		this.totalProtein = totalProtein;
	}

	public double getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(double totalFat) {
		this.totalFat = totalFat;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public double getTotalFibre() {
		return totalFibre;
	}

	public void setTotalFibre(double totalFibre) {
		this.totalFibre = totalFibre;
	}

	public double getTotalSugar() {
		return totalSugar;
	}

	public void setTotalSugar(double totalSugar) {
		this.totalSugar = totalSugar;
	}

	public void setType(MealType type) {
		this.type = type;
	}
    @JsonIgnore
	@ManyToOne
    private FoodItem foodItem;
    
    @JsonIgnore

	 @ManyToOne(optional = false)
	 @JoinColumn(name = "user_id")
	    private Users user;
	 
	 public void calculateNutritionTotals() {
		    if (foodItem != null && servings > 0) {
		        this.totalCalories = foodItem.getCaloriesPerServing() * servings;
		        this.totalCarbs = foodItem.getCarbsPerServing() * servings;
		        this.totalProtein = foodItem.getProteinPerServing() * servings;
		        this.totalFat = foodItem.getFatPerServing() * servings;
		        this.totalFibre = foodItem.getFiberPerServing() * servings;
		        this.totalSugar = foodItem.getSugarPerServing() * servings;
		    }
		}

	
}
