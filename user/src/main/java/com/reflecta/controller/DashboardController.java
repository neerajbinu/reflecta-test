package com.reflecta.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reflecta.service.HealthAPIClientService;

@RestController
@RequestMapping("/dashboard")
		
	public class DashboardController {

    @Autowired
    private HealthAPIClientService healthAPIClientService;

    @GetMapping("/{userId}/data")
    public Map<String, Object> getUserDashboardData(@PathVariable Long userId) {
        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put("food", healthAPIClientService.getFoodData());
        dashboard.put("sleep", healthAPIClientService.getSleepData());
        dashboard.put("mood", healthAPIClientService.getMoodData());
        dashboard.put("exercise", healthAPIClientService.getExerciseData());
        dashboard.put("water", healthAPIClientService.getWaterData());

        return dashboard;
    }
}
