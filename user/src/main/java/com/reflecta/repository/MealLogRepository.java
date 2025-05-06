package com.reflecta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reflecta.entity.MealLog;


@Repository
public interface MealLogRepository extends JpaRepository<MealLog, Long> {
    List<MealLog> findByUserIdAndDate(Long userId, LocalDate date);
    List<MealLog> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
	List<MealLog> findByUserId(Long userId);

    @Query(value = "SELECT * FROM meal_log WHERE user_id = ?1 ORDER BY date DESC LIMIT ?2", nativeQuery = true)
    List<MealLog> findTopByUserIdOrderByDateDesc(Long userId, int limit);
}