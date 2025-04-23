package com.reflecta.api.strava;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reflecta.entity.ExerciseData;
import com.reflecta.entity.Users;
import com.reflecta.enums.ExerciseType;
import com.reflecta.repository.ExerciseDataRepository;
import com.reflecta.repository.UsersRepository;

@Service
public class StravaService {

    private final StravaClient stravaClient;
    private final ExerciseDataRepository exerciseRepo;
    private final UsersRepository userRepo;

    public StravaService(StravaClient stravaClient, ExerciseDataRepository exerciseRepo, UsersRepository userRepo) {
        this.stravaClient = stravaClient;
        this.exerciseRepo = exerciseRepo;
        this.userRepo = userRepo;
    }

    public void syncActivities(String accessToken, Long userId) {
        List<StravaActivityDTO> activities = stravaClient.getRecentActivities(accessToken);

        Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        for (StravaActivityDTO activity : activities) {
            if (!isValidExercise(activity.getType())) continue;

            ExerciseData exercise = new ExerciseData();
            exercise.setDate(activity.getStart_date().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            exercise.setExerciseType(mapToExerciseType(activity.getType()));
            exercise.setDurationMinutes(activity.getMoving_time() / 60.0);
            exercise.setCaloriesBurned(estimateCalories(activity.getMoving_time())); // Use own logic
            exercise.setSource("STRAVA");
            exercise.setStravaActivityId(String.valueOf(activity.getId()));
            exercise.setUser(user);

            // Avoid duplicates
            if (!exerciseRepo.existsByStravaActivityId(activity.getId().toString())) {
                exerciseRepo.save(exercise);
            }
        }
    }

    private boolean isValidExercise(String type) {
        return type.equalsIgnoreCase("Run") || type.equalsIgnoreCase("Ride") || type.equalsIgnoreCase("Walk");
    }

    private ExerciseType mapToExerciseType(String stravaType) {
        return switch (stravaType.toUpperCase()) {
            case "RUN" -> ExerciseType.RUNNING;
            case "RIDE" -> ExerciseType.CYCLING;
            case "WALK" -> ExerciseType.WALKING;
            default -> ExerciseType.OTHER;
        };
    }

    private double estimateCalories(int seconds) {
        return (seconds / 60.0) * 8.0; // basic estimation
    }
}
