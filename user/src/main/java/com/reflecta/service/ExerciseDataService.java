package com.reflecta.service;

import java.util.List;

import com.reflecta.dto.ExerciseDataRequestDTO;
import com.reflecta.dto.ExerciseDataResponseDTO;

public interface ExerciseDataService {
	
	String saveExerciseData(ExerciseDataRequestDTO dto);

    List<ExerciseDataResponseDTO> getAllExercisesForUser(Long userId);

}
