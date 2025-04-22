

//UserJournalRequest.java
package com.reflecta.dto;

import java.time.LocalDate;

public class UserJournalRequest {
 private Long userId;
 private LocalDate startDate;
 private LocalDate endDate;
 
 // Default constructor
 public UserJournalRequest() {}
 
 // Constructor with parameters
 public UserJournalRequest(Long userId, LocalDate startDate, LocalDate endDate) {
     this.userId = userId;
     this.startDate = startDate;
     this.endDate = endDate;
 }
 
 // Getters and setters
 public Long getUserId() {
     return userId;
 }
 
 public void setUserId(Long userId) {
     this.userId = userId;
 }
 
 public LocalDate getStartDate() {
     return startDate;
 }
 
 public void setStartDate(LocalDate startDate) {
     this.startDate = startDate;
 }
 
 public LocalDate getEndDate() {
     return endDate;
 }
 
 public void setEndDate(LocalDate endDate) {
     this.endDate = endDate;
 }
}
