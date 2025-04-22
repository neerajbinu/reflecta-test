package com.reflecta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reflecta.dto.ExerciseDataRequestDTO;
import com.reflecta.dto.ExerciseDataResponseDTO;
import com.reflecta.service.impl.ExerciseDataServiceImplementation;

@RestController
@RequestMapping("/api/exercise")
	public class ExerciseDataController {

	    @Autowired
	    private ExerciseDataServiceImplementation exerciseDataService;

	    @PostMapping("/user/{userId}")
	    public ResponseEntity<String> saveExerciseData(
	            @PathVariable Long userId,
	            @RequestBody ExerciseDataRequestDTO dto) {

	        dto.setUserId(userId);  // Override or ensure userId is set
	        String message = exerciseDataService.saveExerciseData(dto);
	        return ResponseEntity.ok(message);
	    }

	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<ExerciseDataResponseDTO>> getAllExercisesForUser(
	            @PathVariable Long userId) {

	        List<ExerciseDataResponseDTO> exercises = exerciseDataService.getAllExercisesForUser(userId);
	        return ResponseEntity.ok(exercises);
	    }
	


}
