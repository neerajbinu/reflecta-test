package com.reflecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reflecta.entity.FoodItem;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByCategory(String category);
	List<FoodItem> findByNameContainingIgnoreCase(String keyword);
}