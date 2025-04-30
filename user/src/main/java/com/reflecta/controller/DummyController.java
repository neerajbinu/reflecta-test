package com.reflecta.controller;

import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    private final Random random = new Random();

    private final List<String> moodStatuses = Arrays.asList("Happy", "Content", "Stressed", "Sad", "Energetic");
    private final List<String> exerciseTypes = Arrays.asList("Running", "Cycling", "Swimming", "Yoga", "Walking");
    private final List<String> sleepQualities = Arrays.asList("Excellent", "Good", "Fair", "Poor");

    @GetMapping("/exercise")
    public Map<String, Object> getDummyExercise() {
        Map<String, Object> exercise = new HashMap<>();
        exercise.put("date", LocalDate.now().minusDays(random.nextInt(7))); // Last 7 days
        exercise.put("exerciseType", exerciseTypes.get(random.nextInt(exerciseTypes.size())));
        exercise.put("durationMinutes", 20 + random.nextInt(61)); // 20-80 minutes
        exercise.put("caloriesBurned", 100 + random.nextInt(401)); // 100-500 calories
        exercise.put("source", random.nextBoolean() ? "Manual" : "Strava");
        exercise.put("stravaActivityId", UUID.randomUUID().toString());
        return exercise;
    }

    @GetMapping("/sleep")
    public Map<String, Object> getDummySleep() {
        Map<String, Object> sleep = new HashMap<>();
        LocalTime sleepStart = LocalTime.of(22, random.nextInt(60)); // 22:00 to 22:59
        LocalTime sleepEnd = LocalTime.of(6, random.nextInt(60));   // 6:00 to 6:59
        double durationHours = 6 + random.nextDouble() * 3; // 6.0 - 9.0 hours

        sleep.put("date", LocalDate.now().minusDays(random.nextInt(7)));
        sleep.put("sleepStartTime", sleepStart);
        sleep.put("sleepEndTime", sleepEnd);
        sleep.put("durationHours", durationHours);
        sleep.put("sleepQuality", sleepQualities.get(random.nextInt(sleepQualities.size())));
        return sleep;
    }

    @GetMapping("/water")
    public Map<String, Object> getDummyWaterIntake() {
        Map<String, Object> water = new HashMap<>();
        water.put("date", LocalDate.now());
        water.put("totalMl", 500 + random.nextInt(2500)); // 500 - 3000 ml
        water.put("goalMl", 2000);
        return water;
    }

    @GetMapping("/mood")
    public Map<String, Object> getDummyMood() {
        Map<String, Object> mood = new HashMap<>();
        mood.put("date", LocalDate.now());
        mood.put("moodStatus", moodStatuses.get(random.nextInt(moodStatuses.size())));
        mood.put("stressLevel", 1 + random.nextInt(5)); // 1 (very happy) to 5 (stressed)
        return mood;
    }

    @GetMapping("/dailysummary")
    public Map<String, Object> getDummyDailySummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("date", LocalDate.now());
        summary.put("totalSteps", 3000 + random.nextInt(7000)); // 3000-10000 steps
        summary.put("totalCaloriesBurned", 1500 + random.nextInt(1000)); // 1500-2500 kcal
        summary.put("totalCaloriesConsumed", 1800 + random.nextInt(800)); // 1800-2600 kcal
        summary.put("sleepHours", 5 + random.nextDouble() * 3); // 5.0-8.0 hours
        summary.put("moodStatus", moodStatuses.get(random.nextInt(moodStatuses.size())));
        return summary;
    }
}
//http://localhost:8080/dummy/exercise
//http://localhost:8080/dummy/sleep
//http://localhost:8080/dummy/water
//http://localhost:8080/dummy/mood
//http://localhost:8080/dummy/dailysummary

