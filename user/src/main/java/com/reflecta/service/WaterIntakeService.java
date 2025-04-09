package com.reflecta.service;

import com.reflecta.entity.Users;
import com.reflecta.entity.WaterIntake;

import java.util.List;
import java.util.Optional;

public interface WaterIntakeService {

    WaterIntake saveOrUpdateWaterIntake(Users user, int ml);

    List<WaterIntake> getLast7DaysIntake(Users user);

    Optional<WaterIntake> getTodayIntake(Users user);

    void deleteWaterIntake(Long id);
}
