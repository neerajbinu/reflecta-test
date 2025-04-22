package com.reflecta.dto;

import com.reflecta.enums.mealLog.MealType;
import java.time.LocalDate;

public class MealLogRequest {
    private Long userId;
    private Long foodItemId;
    private Double servings;
    private MealType mealType;
    private LocalDate date;
    
    // Default constructor
    public MealLogRequest() {}
    
    // Constructor with parameters
    public MealLogRequest(Long userId, Long foodItemId, Double servings, MealType mealType, LocalDate date) {
        this.userId = userId;
        this.foodItemId = foodItemId;
        this.servings = servings;
        this.mealType = mealType;
        this.date = date;
    }
    
    // Getters and setters
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getFoodItemId() {
        return foodItemId;
    }
    
    public void setFoodItemId(Long foodItemId) {
        this.foodItemId = foodItemId;
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
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
}