package com.reflecta.healthAPI.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reflecta.healthAPI.enums.ExerciseType;
import com.reflecta.healthAPI.enums.MoodStatus;
import com.reflecta.healthAPI.enums.SleepQuality;

@RestController
@RequestMapping("/health")
public class APIController {

    private final Random random = new Random();

    private final List<String> moodStatuses = Arrays.asList("Happy", "Content", "Stressed", "Sad", "Energetic");
    private final List<String> sleepQualities = Arrays.asList("Excellent", "Good", "Fair", "Poor");

    @GetMapping("/exercise")
    public Map<String, Object> getDummyExercise() {
        Map<String, Object> exercise = new HashMap<>();
        
        exercise.put("date", LocalDate.now().minusDays(random.nextInt(7))); // Last 7 days
        
        // Use the ExerciseType enum instead of string list
        ExerciseType[] types = ExerciseType.values();
        ExerciseType randomType = types[random.nextInt(types.length)];
        exercise.put("exerciseType", randomType); // Will be serialized as "CARDIO", "YOGA", etc.
        
        exercise.put("durationMinutes", 20 + random.nextInt(61)); // 20-80 minutes
        exercise.put("caloriesBurned", 100 + random.nextInt(401)); // 100-500 calories
        exercise.put("source", random.nextBoolean() ? "Manual" : "Strava");
        
        return exercise;
    }

    @GetMapping("/sleep")
    public Map<String, Object> getDummySleep() {
        Map<String, Object> sleep = new HashMap<>();
        LocalTime sleepStart = LocalTime.of(22, random.nextInt(60)); // 22:00 to 22:59
        LocalTime sleepEnd = LocalTime.of(6, random.nextInt(60));    // 6:00 to 6:59
        double durationHours = 6 + random.nextDouble() * 3; // 6.0 - 9.0 hours

        sleep.put("date", LocalDate.now().minusDays(random.nextInt(7)));
        sleep.put("sleepStartTime", sleepStart);
        sleep.put("sleepEndTime", sleepEnd);
        sleep.put("durationHours", durationHours);

        // Use SleepQuality enum
        SleepQuality[] qualities = SleepQuality.values();
        SleepQuality randomQuality = qualities[random.nextInt(qualities.length)];
        sleep.put("sleepQuality", randomQuality);

        return sleep;
    }


    @GetMapping("/water")
    public Map<String, Object> getDummyWaterIntake() {
        Map<String, Object> water = new HashMap<>();
        water.put("date", LocalDate.now());
        water.put("totalMl", 500 + random.nextInt(2500)); // 500 - 3000 ml
        water.put("goalMl", 3000);
        return water;
    }

    @GetMapping("/mood")
    public Map<String, Object> getDummyMood() {
        Map<String, Object> mood = new HashMap<>();

        // Random date within the last 7 days
        mood.put("date", LocalDate.now().minusDays(random.nextInt(7)));

        // Random MoodStatus from enum
        MoodStatus[] statuses = MoodStatus.values();
        MoodStatus randomStatus = statuses[random.nextInt(statuses.length)];
        mood.put("moodStatus", randomStatus);
        mood.put("moodScore", randomStatus.getScore()); // Add mood score if needed

        // Stress level between 1 (low stress) to 5 (high stress)
        mood.put("stressLevel", 1 + random.nextInt(5));

        return mood;
    }
    
    @GetMapping("/food")
    public Map<String, Object> getDummyFoodItem() {
        Map<String, Object> foodItem = new HashMap<>();
        foodItem.put("name", "Grilled Chicken");
        foodItem.put("caloriesPerServing", 165);
        foodItem.put("carbsPerServing", 0);
        foodItem.put("proteinPerServing", 31);
        foodItem.put("fatPerServing", 4);
        foodItem.put("fiberPerServing", 0);
        foodItem.put("sugarPerServing", 0);

        return foodItem;
    }


    

//    @GetMapping("/dailysummary")
//    public Map<String, Object> getDummyDailySummary() {
//        Map<String, Object> summary = new HashMap<>();
//        summary.put("date", LocalDate.now());
//        summary.put("totalSteps", 3000 + random.nextInt(7000)); // 3000-10000 steps
//        summary.put("totalCaloriesBurned", 1500 + random.nextInt(1000)); // 1500-2500 kcal
//        summary.put("totalCaloriesConsumed", 1800 + random.nextInt(800)); // 1800-2600 kcal
//        summary.put("sleepHours", 5 + random.nextDouble() * 3); // 5.0-8.0 hours
//        summary.put("moodStatus", moodStatuses.get(random.nextInt(moodStatuses.size())));
//        return summary;
//    }
}