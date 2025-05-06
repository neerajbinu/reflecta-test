package com.reflecta.controller;

import com.reflecta.dto.WaterRequest;
import com.reflecta.entity.Users;
import com.reflecta.entity.WaterIntake;
import com.reflecta.service.UsersService;
import com.reflecta.service.WaterIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    	@PostMapping("{id}/add")
//    	public ResponseEntity<?> addWater(@PathVariable Long id, @RequestBody WaterRequest request) {
    	public ResponseEntity<?> addWater(@PathVariable Long id) {
    	
    	   // Users user = userService.getUserById(request.getUserId());
    		Users user = userService.getUserById(id);
    	    try {
    	        WaterIntake updated = waterIntake.saveOrUpdateWaterIntake(user, 200);
    	        if (updated.getTotalMl() >= 3000) {
    	            return ResponseEntity.ok("Goal reached ! ✅");
    	        } else {
    	            return ResponseEntity.ok("Water intake updated successfully 💧");
    	        }
    	    } catch (IllegalArgumentException e) {
    	        return ResponseEntity.badRequest().body(e.getMessage());
    	    }
    	}


    @GetMapping("/last7/{userId}")
    public List<WaterIntake> getLast7Days(@PathVariable Long userId) {
        Users user = userService.getUserById(userId);
        return waterIntake.getLast7DaysIntake(user);
    }

    @GetMapping("/today/{userId}")
    public WaterIntake getToday(@PathVariable Long userId) {
        Users user = userService.getUserById(userId);
        return waterIntake.getTodayIntake(user).orElse(null);
    }

//    @DeleteMapping("/{id}")
//    public void deleteIntake(@PathVariable Long id) {
//        waterIntake.deleteWaterIntake(id);
//    }
}




