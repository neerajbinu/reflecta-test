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

import com.reflecta.dto.MoodDTO;
import com.reflecta.entity.Mood;
import com.reflecta.service.MoodService;

@RestController
@RequestMapping("/api/mood")
public class MoodController {
	
	@Autowired
	private MoodService ms;
	
	@PostMapping("/add/{userId}")
	public ResponseEntity<Mood> addMood(@PathVariable Long userId, @RequestBody MoodDTO moodDTO) {
	    Mood savedMood=ms.saveMood(moodDTO, userId);
	    return new ResponseEntity<>(savedMood,HttpStatus.CREATED);
	}
	
	@GetMapping("/week/{userId}")
	public ResponseEntity<List<Mood>> getWeeklyMoods(@PathVariable Long userId) {
	    List<Mood> moods = ms.getMoodsForPastWeek(userId);
	    return new ResponseEntity<>(moods,HttpStatus.OK);
	}



}
