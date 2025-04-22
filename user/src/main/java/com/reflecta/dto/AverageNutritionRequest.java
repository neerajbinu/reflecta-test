
//AverageNutritionRequest.java
package com.reflecta.dto;

public class AverageNutritionRequest {
 private Long userId;
 private int days;
 
 // Default constructor
 public AverageNutritionRequest() {
     this.days = 7; // Default days
 }
 
 // Constructor with parameters
 public AverageNutritionRequest(Long userId, int days) {
     this.userId = userId;
     this.days = days;
 }
 
 // Getters and setters
 public Long getUserId() {
     return userId;
 }
 
 public void setUserId(Long userId) {
     this.userId = userId;
 }
 
 public int getDays() {
     return days;
 }
 
 public void setDays(int days) {
     this.days = days;
 }
}
