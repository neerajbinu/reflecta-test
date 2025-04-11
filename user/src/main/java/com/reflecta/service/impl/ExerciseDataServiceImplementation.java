package com.reflecta.service.impl;

import com.reflecta.dto.ExerciseDataRequestDTO;
import com.reflecta.dto.ExerciseDataResponseDTO;
import com.reflecta.entity.ExerciseData;
import com.reflecta.entity.Goal;
import com.reflecta.entity.Users;
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

        if (dto.getGoalId() != null) {
            Goal goal = goalRepository.findById(dto.getGoalId())
                    .orElseThrow(() -> new RuntimeException("Goal not found"));

            if (!goal.getType().equals(GoalType.EXERCISE)) {
                throw new IllegalArgumentException("Goal type mismatch. Expected EXERCISE goal.");
            }

            goal.setCurrentProgress(goal.getCurrentProgress() + dto.getDurationMinutes());
            goalRepository.save(goal);

            data.setGoal(goal);
            message = "Exercise logged and progress added to your goal.";
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
