package com.reflecta.controller;

import com.reflecta.dto.WaterRequest;
import com.reflecta.entity.Users;
import com.reflecta.entity.WaterIntake;
import com.reflecta.service.UsersService;
import com.reflecta.service.WaterIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-intake")
public class WaterIntakeController {

    @Autowired
    private WaterIntakeService waterIntake;

    @Autowired
    private UsersService userService;

//    @PostMapping("/add") --have to give the input in URL
//    public WaterIntake addWater(@RequestParam Long userId, @RequestParam Integer ml) {
//        Users user = userService.getUserById(userId);
//        return waterIntake.saveOrUpdateWaterIntake(user, ml);
//    }
    
    @PostMapping("/add")
    public WaterIntake addWater(@RequestBody WaterRequest request) {
        Users user = userService.getUserById(request.getUserId());
        return waterIntake.saveOrUpdateWaterIntake(user, request.getMl());
    }


    @GetMapping("/last7/{userid}")
    public List<WaterIntake> getLast7Days(@PathVariable Long userId) {
        Users user = userService.getUserById(userId);
        return waterIntake.getLast7DaysIntake(user);
    }

    @GetMapping("/today/{userId}")
    public WaterIntake getToday(@PathVariable Long userId) {
        Users user = userService.getUserById(userId);
        return waterIntake.getTodayIntake(user).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteIntake(@PathVariable Long id) {
        waterIntake.deleteWaterIntake(id);
    }
}




