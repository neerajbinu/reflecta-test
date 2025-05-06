package com.reflecta.service.impl;

import com.reflecta.entity.Users;
import com.reflecta.entity.WaterIntake;
import com.reflecta.repository.WaterIntakeRepository;
import com.reflecta.service.WaterIntakeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WaterIntakeServiceImplementation implements WaterIntakeService {

    @Autowired
    private WaterIntakeRepository waterIntakeRepository;

    @Override
    public WaterIntake saveOrUpdateWaterIntake(Users user, int intakeMl) {
        LocalDate today = LocalDate.now();
        Optional<WaterIntake> optional = waterIntakeRepository.findByUserAndDate(user, today);

        if (optional.isPresent()) {
            WaterIntake existing = optional.get();
            existing.setTotalMl(existing.getTotalMl() + intakeMl);
            WaterIntake UpdatedWaterIntake=waterIntakeRepository.save(existing);
            return UpdatedWaterIntake;
        } else {
            WaterIntake newEntry = new WaterIntake(user, today, intakeMl, 3000);
            return waterIntakeRepository.save(newEntry);
        }
    }

    @Override
    public List<WaterIntake> getLast7DaysIntake(Users user) {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6);
        return waterIntakeRepository.findByUserAndDateBetween(user, sevenDaysAgo, today)
                .stream()
                .sorted(Comparator.comparing(WaterIntake::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<WaterIntake> getTodayIntake(Users user) {
        return waterIntakeRepository.findByUserAndDate(user, LocalDate.now());
    }

    
    @Override
    public void deleteWaterIntake(Long id) {
        waterIntakeRepository.deleteById(id);
    }
}