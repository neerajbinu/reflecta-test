package com.reflecta.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String mealType;
    private double servings;
    private double totalCalories;
    private double totalCarbs;
    private double totalProtein;
    private double totalFat;

    @ManyToOne
    private FoodItem foodItem;

    @ManyToOne
    private Users user;
}
