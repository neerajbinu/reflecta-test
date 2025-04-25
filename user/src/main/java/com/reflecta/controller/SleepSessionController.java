package com.reflecta.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reflecta.dto.SleepSessionDTO;
import com.reflecta.entity.Sleep;
import com.reflecta.entity.SleepSession;
import com.reflecta.repository.SleepSessionRepository;
import com.reflecta.service.SleepService;
import com.reflecta.service.SleepSessionService;

@RestController
@RequestMapping("/api/sleep")
public class SleepSessionController {

	    @Autowired
	    private SleepSessionService sleepSessionService;

	    @Autowired
	    private SleepService sleepService;
	    
	    @Autowired
	    private SleepSessionRepository sleepSessionRepository;
	    
	    

	    // Start a new sleep session
	    @PostMapping("/start/{userId}")
	    public String startSleep(@PathVariable Long userId) {
	        return sleepSessionService.startSleep(userId);
	    }

	    // End the current sleep session
	    @PostMapping("/end/{userId}")
	    public String endSleep(@PathVariable Long userId) {
	        return sleepSessionService.endSleep(userId);
	    }

	    // Get daily sleep summary
	    @GetMapping("/summary/{userId}")
	    public List<Sleep> getDailySummary(
	            @PathVariable Long userId,
	            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
	        return sleepService.getDailySleepSummary(userId, date);
	    }
	    
	    @GetMapping("/sessions")
	    public ResponseEntity<List<SleepSessionDTO>> getSessionsForDate(
	            @RequestParam Long userId,
	            @RequestParam String date) {

	        LocalDate localDate = LocalDate.parse(date);
	        List<SleepSession> sessions = sleepSessionRepository.findByUserIdAndSleep_Date(userId, localDate);

	        List<SleepSessionDTO> sessionDTOs = sessions.stream()
	            .map(session -> new SleepSessionDTO(
	                    session.getId(),
	                    session.getSleepStartTime(),
	                    session.getSleepEndTime(),
	                    session.getDurationHours(),
	                    session.getDisturbances()
	            ))
	            .toList();

	        return ResponseEntity.ok(sessionDTOs);
	    }

	}

