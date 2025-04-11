package com.reflecta.entity;

import java.time.LocalDate;

import com.reflecta.enums.GoalType;
import com.reflecta.enums.MealType;

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
@Table(name = "diet") 

public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double servings;
    private double totalCalories;
    private double totalCarbs;
    private double totalProtein;
    private double totalFat;
    
    
    @Enumerated(EnumType.STRING)
    private MealType type;

    
 // --- Constructors ---
    
    public Diet() {}

    public Diet(Long id, LocalDate date, MealType type, double servings, double totalCalories, double totalCarbs,
			double totalProtein, double totalFat, FoodItem foodItem, Users user) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.servings = servings;
		this.totalCalories = totalCalories;
		this.totalCarbs = totalCarbs;
		this.totalProtein = totalProtein;
		this.totalFat = totalFat;
		this.foodItem = foodItem;
		this.user = user;
	}

	// --- toString ---

	@Override
	public String toString() {
		return "Diet [id=" + id + ", date=" + date + ", type=" + type + ", servings=" + servings
				+ ", totalCalories=" + totalCalories + ", totalCarbs=" + totalCarbs + ", totalProtein=" + totalProtein
				+ ", totalFat=" + totalFat + ", foodItem=" + foodItem + "]";
				
//				, user=" + user + ", getId()=" + getId()
//				+ ", getDate()=" + getDate() + ", getMealType()=" + type() + ", getServings()=" + getServings()
//				+ ", getTotalCalories()=" + getTotalCalories() + ", getTotalCarbs()=" + getTotalCarbs()
//				+ ", getTotalProtein()=" + getTotalProtein() + ", getTotalFat()=" + getTotalFat() + ", getFoodItem()="
//				+ getFoodItem() + ", getUser()=" + getUser() + ", getClass()=" + getClass() + ", hashCode()="
//				+ hashCode() + ", toString()=" + super.toString() + "]";
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

	public MealType getType() {
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

	@ManyToOne
    private FoodItem foodItem;

	 @ManyToOne(optional = false)
	 @JoinColumn(name = "user_id")
	    private Users user;
	 
	//added for creating a relation to implement Goal
	 @ManyToOne
	 @JoinColumn(name = "goal_id")
	 private Goal goal;

}
