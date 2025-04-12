
//UserMealRequest.java
package com.reflecta.dto;

import java.time.LocalDate;

public class UserMealRequest {
 private Long userId;
 private LocalDate date;
 
 // Default constructor
 public UserMealRequest() {}
 
 // Constructor with parameters
 public UserMealRequest(Long userId, LocalDate date) {
     this.userId = userId;
     this.date = date;
 }
 
 // Getters and setters
 public Long getUserId() {
     return userId;
 }
 
 public void setUserId(Long userId) {
     this.userId = userId;
 }
 
 public LocalDate getDate() {
     return date;
 }
 
 public void setDate(LocalDate date) {
     this.date = date;
 }
}
