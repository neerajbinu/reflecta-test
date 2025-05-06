
package com.reflecta.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reflecta.entity.FoodItem;
import com.reflecta.entity.MealLog;
import com.reflecta.entity.Users;
import com.reflecta.enums.mealLog.MealType;
import com.reflecta.repository.FoodItemRepository;
import com.reflecta.repository.GoalRepository;
import com.reflecta.repository.MealLogRepository;
import com.reflecta.repository.UsersRepository;
import com.reflecta.service.FoodService;

@Service
public class FoodServiceImplementation implements FoodService {
    
	@Autowired
    private  FoodItemRepository foodItemRepository;
	
	@Autowired
    private  MealLogRepository mealLogRepository;
    
	@Autowired
    private  UsersRepository usersRepository;
    
	@Autowired
	private GoalRepository goalRepository;
	
    // Food item operations
    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }
    
    @Override
    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Food item not found with id: " + id));
    }
    
    @Override
    public List<FoodItem> searchFoodItems(String keyword) {
        return foodItemRepository.findByNameContainingIgnoreCase(keyword);
    }
    
    @Override
    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }
    
    // Meal logging operations
    @Override
    public MealLog logMeal(Long userId, Long foodItemId, double servings, MealType mealType, LocalDate date) {
        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            
        FoodItem foodItem = getFoodItemById(foodItemId);
        
        MealLog mealLog = new MealLog();
        mealLog.setUser(user);
        mealLog.setFoodItem(foodItem);
        mealLog.setServings(servings);
        mealLog.setMealType(mealType);
        mealLog.setDate(date != null ? date : LocalDate.now());
        
        // Calculate nutrition totals
        mealLog.calculateNutritionTotals();
        
        return mealLogRepository.save(mealLog);
    }
    
    @Override
    public List<MealLog> getUserMealsForDay(Long userId, LocalDate date) {
        return mealLogRepository.findByUserIdAndDate(userId, date);
    }
    
    @Override
    public Map<String, Double> getNutritionSummaryForDay(Long userId, LocalDate date) {
        List<MealLog> meals = getUserMealsForDay(userId, date);
        return calculateNutritionTotals(meals);
    }
    
    private Map<String, Double> calculateNutritionTotals(List<MealLog> meals) {
        double totalCalories = 0;
        double totalCarbs = 0;
        double totalProtein = 0;
        double totalFat = 0;
        double totalFiber = 0;
        double totalSugar = 0;
        
        for (MealLog meal : meals) {
            totalCalories += meal.getTotalCalories();
            totalCarbs += meal.getTotalCarbs();
            totalProtein += meal.getTotalProtein();
            totalFat += meal.getTotalFat();
            if (meal.getTotalFibre() != 0) totalFiber += meal.getTotalFibre();
            if (meal.getTotalSugar() != 0) totalSugar += meal.getTotalSugar();
        }
        
        Map<String, Double> summary = new HashMap<>();
        summary.put("calories", totalCalories);
        summary.put("carbs", totalCarbs);
        summary.put("protein", totalProtein);
        summary.put("fat", totalFat);
        summary.put("fiber", totalFiber);
        summary.put("sugar", totalSugar);
        
        return summary;
    }
    
    @Override
    public Map<MealType, List<MealLog>> getMealsByTypeForDay(Long userId, LocalDate date) {
        List<MealLog> allMeals = getUserMealsForDay(userId, date);
        
        Map<MealType, List<MealLog>> mealsByType = new HashMap<>();
        for (MealType type : MealType.values()) {
            final MealType currentType = type; // Need this for lambda
            List<MealLog> mealsOfType = allMeals.stream()
                .filter(meal -> meal.getMealType() == currentType)
                .collect(Collectors.toList());
            
            mealsByType.put(type, mealsOfType);
        }
        
        return mealsByType;
    }
    
    @Override
    public void deleteMeal(Long mealId) {
        mealLogRepository.deleteById(mealId);
    }
    
    @Override
    public MealLog updateMeal(MealLog mealLog) {
        mealLog.calculateNutritionTotals();
        return mealLogRepository.save(mealLog);
    }
    
    // Complete food history methods
    @Override    
    public List<MealLog> getAllUserMeals(Long userId) {
        return mealLogRepository.findByUserId(userId);
    }

    
    @Override
    public List<FoodItem> getUserFrequentFoods(Long userId, int limit) {
        List<MealLog> allMeals = getAllUserMeals(userId);
        
        // Group meals by food item and count occurrences
        Map<FoodItem, Long> foodCounts = allMeals.stream()
            .collect(Collectors.groupingBy(MealLog::getFoodItem, Collectors.counting()));
        
        // Sort by frequency and limit results
        return foodCounts.entrySet().stream()
            .sorted(Map.Entry.<FoodItem, Long>comparingByValue().reversed())
            .limit(limit)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
    
    @Override
    public Map<String, Object> getUserNutritionStats(Long userId) {
        List<MealLog> allMeals = getAllUserMeals(userId);
        
        // Calculate overall nutrition totals
        Map<String, Double> totals = calculateNutritionTotals(allMeals);
        
        // Get min, max, avg calories per day
        Map<LocalDate, Double> caloriesByDay = allMeals.stream()
            .collect(Collectors.groupingBy(
                MealLog::getDate,
                Collectors.summingDouble(MealLog::getTotalCalories)
            ));
        
        double minCalories = caloriesByDay.isEmpty() ? 0 : 
                             caloriesByDay.values().stream().min(Double::compare).orElse(0.0);
        double maxCalories = caloriesByDay.isEmpty() ? 0 : 
                             caloriesByDay.values().stream().max(Double::compare).orElse(0.0);
        double avgCalories = caloriesByDay.isEmpty() ? 0 : 
                             caloriesByDay.values().stream().mapToDouble(d -> d).average().orElse(0.0);
        
        // Get most common meal types
        Map<MealType, Long> mealTypeCounts = allMeals.stream()
            .collect(Collectors.groupingBy(MealLog::getMealType, Collectors.counting()));
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalMeals", allMeals.size());
        stats.put("nutritionTotals", totals);
        stats.put("minDailyCalories", minCalories);
        stats.put("maxDailyCalories", maxCalories);
        stats.put("avgDailyCalories", avgCalories);
        stats.put("mealTypeCounts", mealTypeCounts);
        stats.put("uniqueFoods", allMeals.stream().map(MealLog::getFoodItem).distinct().count());
        
        return stats;
    }
    
    @Override
    public Map<String, Double> getUserAverageNutrition(Long userId, int days) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days - 1);
        
        List<MealLog> mealLogs = mealLogRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
        
        if (mealLogs.isEmpty()) {
            return Map.of(
                "avgCalories", 0.0,
                "avgCarbs", 0.0,
                "avgProtein", 0.0,
                "avgFat", 0.0
            );
        }
        
        Map<LocalDate, List<MealLog>> mealsByDay = mealLogs.stream()
            .collect(Collectors.groupingBy(MealLog::getDate));
        
        Map<String, Double> totalsByDay = new HashMap<>();
        
        for (List<MealLog> dayMeals : mealsByDay.values()) {
            Map<String, Double> dayTotals = calculateNutritionTotals(dayMeals);
            
            for (Map.Entry<String, Double> entry : dayTotals.entrySet()) {
                totalsByDay.merge(
                    "avg" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1),
                    entry.getValue(),
                    Double::sum
                );
            }
        }
        
        int daysWithMeals = mealsByDay.size();
        totalsByDay.replaceAll((k, v) -> v / daysWithMeals);
        
        return totalsByDay;
    }
    
    @Override
    public List<MealLog> getRecentMeals(Long userId, int limit) {
        return mealLogRepository.findTopByUserIdOrderByDateDesc(userId, limit);
    }
    
    @Override
    public Map<String, Integer> getFoodConsumptionCount(Long userId) {
        List<MealLog> allMeals = getAllUserMeals(userId);
        
        return allMeals.stream()
            .collect(Collectors.groupingBy(
                meal -> meal.getFoodItem().getName(),
                Collectors.summingInt(meal -> 1)
            ));
    }

	
}