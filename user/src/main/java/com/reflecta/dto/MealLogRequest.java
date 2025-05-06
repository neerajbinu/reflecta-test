package com.reflecta.dto;

import com.reflecta.enums.mealLog.MealType;
import java.time.LocalDate;

public class MealLogRequest {
    private Double servings;
    private MealType mealType;
    private String name;
    
    // Default constructor
    public MealLogRequest() {}
    
    // Constructor with parameters
    public MealLogRequest( String name,Double servings, MealType mealType) {
       this.name=name;
    	this.servings = servings;
        this.mealType = mealType;
    }
    
    // Getters and setters
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getServings() {
        return servings;
    }

	public void setServings(Double servings) {
        this.servings = servings;
    }
    
    public MealType getMealType() {
        return mealType;
    }
    
    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }
    
}