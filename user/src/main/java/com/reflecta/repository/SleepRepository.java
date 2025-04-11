package com.reflecta.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.Sleep;

public interface SleepRepository extends JpaRepository<Sleep,Long> {
	Optional<Sleep> findByUserIdAndDate(Long userId, LocalDate date);
	
}
