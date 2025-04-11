package com.reflecta.dto;

public class SleepSessionEndRequestDTO {
	
	    private Long userId;
	    private int disturbances;

	    public SleepSessionEndRequestDTO() {
	    }

	    public SleepSessionEndRequestDTO(Long userId, int disturbances) {
	        this.userId = userId;
	        this.disturbances = disturbances;
	    }

	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public int getDisturbances() {
	        return disturbances;
	    }

	    public void setDisturbances(int disturbances) {
	        this.disturbances = disturbances;
	    }
	
}


