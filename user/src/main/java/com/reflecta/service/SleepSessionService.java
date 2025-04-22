package com.reflecta.service;

import java.time.LocalDate;

import com.reflecta.dto.SleepSummaryDTO;
import com.reflecta.entity.Sleep;

public interface SleepSessionService {

	    /**
	     * Starts a new sleep session for a specific user.
	     *
	     * @param userId ID of the user starting the sleep session
	     * @return A status message confirming the session has started
	     */
	    String startSleep(Long userId);

	    /**
	     * Ends the most recent sleep session for a specific user, calculates duration,
	     * and updates related sleep summary data.
	     *
	     * @param userId ID of the user ending the sleep session
	     * @return A status message with total sleep duration
	     */
	    String endSleep(Long userId);
	    
	    //Sleep getDailySleepSummary(Long userId, LocalDate date);
	    
	    
	    SleepSummaryDTO getDailySummary(Long userId, LocalDate date);


	
}
