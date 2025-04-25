package com.reflecta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reflecta.dto.GoalRequestDTO;
import com.reflecta.entity.Goal;
import com.reflecta.service.impl.GoalServiceImplementation;

@RestController
@RequestMapping("/api/goal")
public class GoalController {
	
	@Autowired
	private GoalServiceImplementation gs;

	@PostMapping("/{userId}/create")
	public ResponseEntity<Goal> createGoal(@PathVariable Long userId, @RequestBody GoalRequestDTO goalRequestDTO) {
           Goal savedGoal= gs.createGoal(userId,goalRequestDTO);
            return new ResponseEntity<Goal>(savedGoal,HttpStatus.CREATED);
}
	@GetMapping("/{userId}")
    public ResponseEntity<List<Goal>> getGoalsByUser(@PathVariable Long userId) {
        List<Goal> goals = gs.getGoalsByUser(userId);
        if (goals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 if no goals are found
        }
        return new ResponseEntity<>(goals, HttpStatus.OK);  // Return 200 with the list of goals
    }
	
}	
