package com.reflecta.service;

import java.time.LocalDate;
import java.util.List;

import com.reflecta.entity.Sleep;
import com.reflecta.entity.SleepSession;
import com.reflecta.enums.SleepQuality;

public interface SleepService {

    	SleepQuality estimateSleepQuality(double duration, int disturbances);

	    List<Sleep> getDailySleepSummary(Long userId, LocalDate date);

	    double calculateTotalSleepDuration(Long userId, LocalDate date);

	    void updateSleepSummary(SleepSession session);
	    
	 

}
