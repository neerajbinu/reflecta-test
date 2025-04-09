package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
	
}

