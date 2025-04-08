package com.reflecta.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String mealType;
    private double servings;
    private double totalCalories;
    private double totalCarbs;
    private double totalProtein;
    private double totalFat;
    
    public Diet() {}

    public Diet(Long id, LocalDate date, String mealType, double servings, double totalCalories, double totalCarbs,
			double totalProtein, double totalFat, FoodItem foodItem, Users user) {
		super();
		this.id = id;
		this.date = date;
		this.mealType = mealType;
		this.servings = servings;
		this.totalCalories = totalCalories;
		this.totalCarbs = totalCarbs;
		this.totalProtein = totalProtein;
		this.totalFat = totalFat;
		this.foodItem = foodItem;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Diet [id=" + id + ", date=" + date + ", mealType=" + mealType + ", servings=" + servings
				+ ", totalCalories=" + totalCalories + ", totalCarbs=" + totalCarbs + ", totalProtein=" + totalProtein
				+ ", totalFat=" + totalFat + ", foodItem=" + foodItem + ", user=" + user + ", getId()=" + getId()
				+ ", getDate()=" + getDate() + ", getMealType()=" + getMealType() + ", getServings()=" + getServings()
				+ ", getTotalCalories()=" + getTotalCalories() + ", getTotalCarbs()=" + getTotalCarbs()
				+ ", getTotalProtein()=" + getTotalProtein() + ", getTotalFat()=" + getTotalFat() + ", getFoodItem()="
				+ getFoodItem() + ", getUser()=" + getUser() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

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

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
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

    @ManyToOne
    private Users user;
}
