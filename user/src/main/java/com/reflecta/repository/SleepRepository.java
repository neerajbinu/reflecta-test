package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.Sleep;

public interface SleepRepository extends JpaRepository<Sleep,Long> {

}
