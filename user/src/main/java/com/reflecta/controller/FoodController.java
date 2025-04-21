package com.reflecta.controller;

import com.reflecta.entity.MealLog;
import com.reflecta.entity.FoodItem;
import com.reflecta.enums.mealLog.MealType;
import com.reflecta.service.FoodService;
import com.reflecta.dto.MealLogRequest;
import com.reflecta.dto.UserMealRequest;
import com.reflecta.dto.UserJournalRequest;
import com.reflecta.dto.FrequentFoodRequest;
import com.reflecta.dto.AverageNutritionRequest;
import com.reflecta.dto.RecentMealsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    
    private final FoodService foodService;
    
    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    
    // Food item endpoints
    @GetMapping("/items")
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return ResponseEntity.ok(foodService.getAllFoodItems());
    }
    
    @GetMapping("/items/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.getFoodItemById(id));
    }
    
    @GetMapping("/items/search")
    public ResponseEntity<List<FoodItem>> searchFoodItems(@RequestParam String keyword) {
        return ResponseEntity.ok(foodService.searchFoodItems(keyword));
    }
    
    @PostMapping("/items")
    public ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItem foodItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.addFoodItem(foodItem));
    }
    
    // Meal logging endpoints
    @PostMapping("/meals/log")
    public ResponseEntity<MealLog> logMeal(@RequestBody MealLogRequest request) {
        MealLog entry = foodService.logMeal(
            request.getUserId(),
            request.getFoodItemId(),
            request.getServings(),
            request.getMealType(),
            request.getDate()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(entry);
    }
    
    @PostMapping("/meals/get")
    public ResponseEntity<List<MealLog>> getUserMeals(@RequestBody UserMealRequest request) {
        return ResponseEntity.ok(foodService.getUserMealsForDay(request.getUserId(), request.getDate()));
    }
    
    @PostMapping("/meals/summary")
    public ResponseEntity<Map<String, Double>> getNutritionSummary(@RequestBody UserMealRequest request) {
        return ResponseEntity.ok(foodService.getNutritionSummaryForDay(request.getUserId(), request.getDate()));
    }
    
    @PostMapping("/meals/by-type")
    public ResponseEntity<Map<MealType, List<MealLog>>> getMealsByType(@RequestBody UserMealRequest request) {
        return ResponseEntity.ok(foodService.getMealsByTypeForDay(request.getUserId(), request.getDate()));
    }
    
    
    @DeleteMapping("/meals/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        foodService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/meals/{id}")
    public ResponseEntity<MealLog> updateMeal(@PathVariable Long id, @RequestBody MealLog mealLog) {
        if (!id.equals(mealLog.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foodService.updateMeal(mealLog));
    }
    
    // Complete history endpoints
    @PostMapping("/user/meals/all")
    public ResponseEntity<List<MealLog>> getAllUserMeals(@RequestBody UserMealRequest request) {
        return ResponseEntity.ok(foodService.getAllUserMeals(request.getUserId()));
    }
    
    @PostMapping("/user/foods/frequent")
    public ResponseEntity<List<FoodItem>> getUserFrequentFoods(@RequestBody FrequentFoodRequest request) {
        return ResponseEntity.ok(foodService.getUserFrequentFoods(request.getUserId(), request.getLimit()));
    }
    
    @PostMapping("/user/stats")
    public ResponseEntity<Map<String, Object>> getUserNutritionStats(@RequestBody UserMealRequest request) {
        return ResponseEntity.ok(foodService.getUserNutritionStats(request.getUserId()));
    }
    
    @PostMapping("/user/average")
    public ResponseEntity<Map<String, Double>> getUserAverageNutrition(@RequestBody AverageNutritionRequest request) {
        return ResponseEntity.ok(foodService.getUserAverageNutrition(request.getUserId(), request.getDays()));
    }
    
    @PostMapping("/user/meals/recent")
    public ResponseEntity<List<MealLog>> getRecentMeals(@RequestBody RecentMealsRequest request) {
        return ResponseEntity.ok(foodService.getRecentMeals(request.getUserId(), request.getLimit()));
    }
    
    @PostMapping("/user/consumption")
    public ResponseEntity<Map<String, Integer>> getFoodConsumptionCount(@RequestBody UserMealRequest request) {
        return ResponseEntity.ok(foodService.getFoodConsumptionCount(request.getUserId()));
    }
}