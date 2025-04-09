package com.reflecta.service;

import com.reflecta.entity.Users;
import com.reflecta.entity.WaterIntake;
import com.reflecta.repository.WaterIntakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WaterIntakeService {

    @Autowired
    private WaterIntakeRepository waterIntakeRepository;

    public WaterIntake saveOrUpdateWaterIntake(Users user, Integer intakeMl) {
        LocalDate today = LocalDate.now();
        Optional<WaterIntake> optional = waterIntakeRepository.findByUserAndDate(user, today);

        if (optional.isPresent()) {
            WaterIntake existing = optional.get();
            existing.setTotalMl(existing.getTotalMl() + intakeMl);
            return waterIntakeRepository.save(existing);
        } else {
            WaterIntake newEntry = new WaterIntake(user, today, intakeMl, 2000);
            return waterIntakeRepository.save(newEntry);
        }
    }

    public List<WaterIntake> getLast7DaysIntake(Users user) {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6);
        return waterIntakeRepository.findByUserAndDateBetween(user, sevenDaysAgo, today)
                .stream()
                .sorted(Comparator.comparing(WaterIntake::getDate))
                .collect(Collectors.toList());
    }

    public Optional<WaterIntake> getTodayIntake(Users user) {
        return waterIntakeRepository.findByUserAndDate(user, LocalDate.now());
    }

    public void deleteWaterIntake(Long id) {
        waterIntakeRepository.deleteById(id);
    }
}
