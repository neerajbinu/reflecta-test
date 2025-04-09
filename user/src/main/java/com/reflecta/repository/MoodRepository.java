package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.Mood;

public interface MoodRepository extends JpaRepository<Mood, Long> {
	
}