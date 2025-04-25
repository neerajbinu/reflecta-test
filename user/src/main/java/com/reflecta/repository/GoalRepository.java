package com.reflecta.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.Goal;
import com.reflecta.enums.Frequency;
import com.reflecta.enums.GoalStatus;
import com.reflecta.enums.GoalType;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	
	Optional<Goal> findByUserIdAndTypeAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
		    Long userId, 
		    GoalType type, 
		    GoalStatus status, 
		    LocalDate currentDate1, 
		    LocalDate currentDate2
		);
	
	List<Goal> findByUserId(Long userId);
	
	List<Goal> findAllByFrequencyAndStatus(Frequency frequency, GoalStatus status);



	
}