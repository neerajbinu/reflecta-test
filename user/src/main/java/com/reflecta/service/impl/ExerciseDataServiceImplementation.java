package com.reflecta.service.impl;

import com.reflecta.dto.ExerciseDataRequestDTO;
import com.reflecta.dto.ExerciseDataResponseDTO;
import com.reflecta.entity.ExerciseData;
import com.reflecta.entity.Goal;
import com.reflecta.entity.Users;
import com.reflecta.enums.GoalStatus;
import com.reflecta.enums.GoalMetric;
import com.reflecta.enums.GoalType;
import com.reflecta.repository.ExerciseDataRepository;
import com.reflecta.repository.GoalRepository;
import com.reflecta.repository.UsersRepository;
import com.reflecta.service.ExerciseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseDataServiceImplementation implements ExerciseDataService {

    @Autowired
    private ExerciseDataRepository exerciseDataRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public String saveExerciseData(ExerciseDataRequestDTO dto) {

        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExerciseData data = new ExerciseData();
        data.setDate(dto.getDate());
        data.setDurationMinutes(dto.getDurationMinutes());
        data.setCaloriesBurned(dto.getCaloriesBurned());
        data.setExerciseType(dto.getExerciseType());
        data.setUser(user);

        String message = "Exercise logged successfully.";

        // Automatically fetch the active EXERCISE goal
        Goal goal = goalRepository
                .findByUserAndTypeAndStatus(user, GoalType.EXERCISE, GoalStatus.ONGOING)
                .orElse(null);

        if (goal != null) {
            if (goal.getMetric() == null) {
                throw new IllegalArgumentException("Goal metric not specified.");
            }

            // Only consider CALORIES_BURNED for exercise goals
            if (goal.getMetric() == GoalMetric.CALORIES_BURNED) {
                goal.setCurrentProgress(goal.getCurrentProgress() + dto.getCaloriesBurned());
            } else {
                throw new IllegalArgumentException("Unsupported metric type for exercise goal.");
            }

            // Check if the goal has been completed
            if (goal.getCurrentProgress() >= goal.getTarget()) {
                goal.setStatus(GoalStatus.COMPLETED);
            }

            goalRepository.save(goal);
            data.setGoal(goal);

            double percentage = ((double) goal.getCurrentProgress() / goal.getTarget()) * 100;
            message = String.format("Exercise logged. Goal progress: %.2f%%. %s",
                    percentage,
                    goal.getStatus().equals(GoalStatus.COMPLETED) ? "Goal completed!" : "Keep going!");
        }

        exerciseDataRepository.save(data);
        return message;
    }

    @Override
    public List<ExerciseDataResponseDTO> getAllExercisesForUser(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return exerciseDataRepository.findByUser(user).stream().map(data -> {
            ExerciseDataResponseDTO dto = new ExerciseDataResponseDTO();
            dto.setId(data.getId());
            dto.setUserName(user.getName());
            dto.setDate(data.getDate());
            dto.setExerciseType(data.getExerciseType());
            dto.setDurationMinutes(data.getDurationMinutes());
            dto.setCaloriesBurned(data.getCaloriesBurned());
            dto.setGoalId(data.getGoal() != null ? data.getGoal().getId() : null);
            return dto;
        }).collect(Collectors.toList());
    }
}
