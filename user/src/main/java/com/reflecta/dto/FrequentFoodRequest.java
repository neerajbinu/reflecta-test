
//FrequentFoodsRequest.java
package com.reflecta.dto;

public class FrequentFoodRequest {
 private Long userId;
 private int limit;
 
 // Default constructor
 public FrequentFoodRequest() {
     this.limit = 10; // Default limit
 }
 
 // Constructor with parameters
 public FrequentFoodRequest(Long userId, int limit) {
     this.userId = userId;
     this.limit = limit;
 }
 
 // Getters and setters
 public Long getUserId() {
     return userId;
 }
 
 public void setUserId(Long userId) {
     this.userId = userId;
 }
 
 public int getLimit() {
     return limit;
 }
 
 public void setLimit(int limit) {
     this.limit = limit;
 }
}