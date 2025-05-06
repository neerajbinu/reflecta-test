package com.reflecta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.Mood;

public interface MoodRepository extends JpaRepository<Mood, Long> {

	List<Mood> findByUserIdAndDateBetween(Long userId, LocalDate weekAgo, LocalDate today);
	
}