package com.reflecta.service;

import com.reflecta.entity.MealLog;
import com.reflecta.enums.mealLog.MealType;
import com.reflecta.dto.MealLogRequest;
import com.reflecta.entity.FoodItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FoodService {
    // Food item operations
    List<FoodItem> getAllFoodItems();
    FoodItem getFoodItemById(Long id);
    List<FoodItem> searchFoodItems(String keyword);
    FoodItem addFoodItem(FoodItem foodItem);
    
    // Meal logging operations
    MealLog logMeal(Long userId, MealLogRequest request);
    List<MealLog> getUserMealsForDay(Long userId, LocalDate date);
    Map<String, Double> getNutritionSummaryForDay(Long userId, LocalDate date);
    Map<MealType, List<MealLog>> getMealsByTypeForDay(Long userId, LocalDate date);
    void deleteMeal(Long mealId);
    MealLog updateMeal(MealLog mealLog);
    
    // Complete food history methods
    List<MealLog> getAllUserMeals(Long userId);
    List<FoodItem> getUserFrequentFoods(Long userId, int limit);
    Map<String, Object> getUserNutritionStats(Long userId);
    Map<String, Double> getUserAverageNutrition(Long userId, int days);
    List<MealLog> getRecentMeals(Long userId, int limit);
    Map<String, Integer> getFoodConsumptionCount(Long userId);
//	MealLog logMeal(Long userId, Long foodItemId, double servings, com.reflecta.enums.mealLog.MealType mealType,
//			LocalDate date);
	
}