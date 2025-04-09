package com.reflecta.repository;
import com.reflecta.entity.WaterIntake;
import com.reflecta.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WaterIntakeRepository extends JpaRepository<WaterIntake, Long>{
	
	Optional<WaterIntake> findByUserAndDate(Users user, LocalDate date);
    List<WaterIntake> findByUserAndDateBetween(Users user, LocalDate startDate, LocalDate endDate);


}





