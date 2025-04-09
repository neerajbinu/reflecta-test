package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.MentalHealthAlert;

public interface MentalHealthAlertRepository extends JpaRepository<MentalHealthAlert, Long> {
	
}
