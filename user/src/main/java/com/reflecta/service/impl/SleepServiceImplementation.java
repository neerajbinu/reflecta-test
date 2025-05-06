package com.reflecta.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reflecta.entity.Sleep;
import com.reflecta.entity.SleepSession;
import com.reflecta.enums.SleepQuality;
import com.reflecta.repository.SleepRepository;
import com.reflecta.repository.SleepSessionRepository;
import com.reflecta.service.SleepService;

@Service
public class SleepServiceImplementation implements SleepService {
	
	@Autowired
	private SleepRepository sleepRepository;
	
	  @Autowired
	    private SleepSessionRepository sleepSessionRepository;

	    // Method to estimate sleep quality based on total duration
	  @Override
	  public SleepQuality estimateSleepQuality(double duration, int disturbances)   
	  {
	  		int score = 0;

	        if (duration >= 7) score += 2;
	        else if (duration >= 6) score += 1;

	        if (disturbances == 0) score += 2;
	        else if (disturbances <= 2) score += 1;

	        if (score >= 4) return SleepQuality.EXCELLENT;
	        else if (score == 3) return SleepQuality.GOOD;
	        else if (score == 2) return SleepQuality.AVERAGE;
	        else return SleepQuality.POOR;
	    }
	

	    
	  // Method to get daily sleep summary for a user
	  @Override
	    public List<Sleep> getDailySleepSummary(Long userId, LocalDate date) {
//	        Optional<Sleep> sleepOpt = sleepRepository.findByUserIdAndDate(userId, date);
//	        if (sleepOpt.isPresent()) {
//	            return sleepOpt.get();
//	        }
//	        return null; // return null if no sleep record exists for that user and date
		  
		  List<Sleep>sleepList=sleepRepository.findByUserIdAndDate(userId, date);
		  return sleepList;
	    }

	    // Method to calculate the total sleep duration for a user in a given day
	  @Override
	    public double calculateTotalSleepDuration(Long userId, LocalDate date) {
	        List<SleepSession> sessions = sleepSessionRepository.findByUserIdAndSleep_Date(userId, date);
	        double totalDuration = 0.0;
	        for (SleepSession session : sessions) {
	            totalDuration += session.getDurationHours();
	        }
	        return totalDuration; //totalduration is used to count the hours slept by the person
	    }

	    // Method to update sleep record (daily summary) after session ends
	  @Override
	    public void updateSleepSummary(SleepSession session) {
	        Sleep sleep = session.getSleep();
	        Long userId = sleep.getUser().getId();
	        LocalDate date = sleep.getDate();

	        double totalDuration = calculateTotalSleepDuration(userId, date);

	        // Calculate total disturbances for the day
	        List<SleepSession> allSessions = sleepSessionRepository.findByUserIdAndSleep_Date(userId, date);
	        int totalDisturbances = allSessions.size() - 1;


	        sleep.setDurationHours(totalDuration);
	        sleep.setSleepQuality(estimateSleepQuality(totalDuration, totalDisturbances));

	        sleepRepository.save(sleep);
	    }
	  
	  @Override
	  public double calculateWeeklySleepQualityScore(Long userId) {
		  	LocalDate today = LocalDate.now();
		    LocalDate weekAgo = today.minusDays(6); // 7-day range including today

		    // Fetch  sleep entries
		    List<Sleep> weeklySleep = sleepRepository.findByUserIdAndDateBetween(userId, weekAgo, today);


		    // --- Count days with POOR sleep quality ---
		    long poorSleepCount = 0;
		    for (Sleep sleep : weeklySleep) {
		        if (sleep.getSleepQuality() == SleepQuality.POOR) {
		            poorSleepCount++;
		        }
		    }
		    return poorSleepCount;
	  }	    
}


