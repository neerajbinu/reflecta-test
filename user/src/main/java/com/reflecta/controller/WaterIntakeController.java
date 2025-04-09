package com.reflecta.controller;

import com.reflecta.entity.Users;
import com.reflecta.entity.WaterIntake;
import com.reflecta.service.UserService;
import com.reflecta.service.WaterIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-intake")
public class WaterIntakeController {

    @Autowired
    private WaterIntakeService waterIntakeService;

    @Autowired
    private UserService userService;

    // Example: POST /api/water-intake/add?userId=1&ml=250
    @PostMapping("/add")
    public WaterIntake addWater(@RequestParam Long userId, @RequestParam Integer ml) {
        Users user = userService.getUserById(userId);
        return waterIntakeService.saveOrUpdateWaterIntake(user, ml);
    }

    // Example: GET /api/water-intake/last7?userId=1
    @GetMapping("/last7")
    public List<WaterIntake> getLast7Days(@RequestParam Long userId) {
        Users user = userService.getUserById(userId);
        return waterIntakeService.getLast7DaysIntake(user);
    }

    // Example: GET /api/water-intake/today?userId=1
    @GetMapping("/today")
    public WaterIntake getToday(@RequestParam Long userId) {
        Users user = userService.getUserById(userId);
        return waterIntakeService.getTodayIntake(user).orElse(null);
    }

    // Optional
    @DeleteMapping("/{id}")
    public void deleteIntake(@PathVariable Long id) {
        waterIntakeService.deleteWaterIntake(id);
    }
}
