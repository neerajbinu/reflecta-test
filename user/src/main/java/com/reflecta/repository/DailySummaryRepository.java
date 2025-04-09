package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.DailySummary;

public interface DailySummaryRepository extends JpaRepository <DailySummary,Long> {

}
