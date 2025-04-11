package com.reflecta.service.impl;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reflecta.dto.SleepSummaryDTO;
import com.reflecta.entity.Sleep;
import com.reflecta.entity.SleepSession;
import com.reflecta.entity.Users;
import com.reflecta.repository.SleepRepository;
import com.reflecta.repository.SleepSessionRepository;
import com.reflecta.service.SleepService;
import com.reflecta.service.SleepSessionService;
import com.reflecta.service.UsersService;

@Service
public class SleepSessionServiceImplementation implements SleepSessionService {

    @Autowired
    private SleepSessionRepository sleepSessionRepository;

    @Autowired
    private UsersService userService;

    @Autowired
    private SleepService sleepService;
    
    @Autowired
    private SleepRepository sleepRepository;

    // Start a new sleep session
    @Override
    public String startSleep(Long userId) {
        Users user = userService.getUserById(userId);

        Sleep sleep = new Sleep();
        sleep.setUser(user);
        sleep.setDate(LocalDate.now());

        sleepRepository.save(sleep);

        SleepSession session = new SleepSession();
        session.setUser(user);
        session.setSleepStartTime(LocalTime.now());
        session.setSleepEndTime(null); // Not yet ended
        session.setDurationHours(0); // Not yet calculated
        session.setSleep(sleep);

        sleepSessionRepository.save(session);

        return "Sleep session started.";
    }

    // End the latest sleep session and calculate everything
    @Override
    public String endSleep(Long userId) {
    	    Optional<SleepSession> latestSessionOpt = sleepSessionRepository
    	            .findTopByUserIdOrderByIdDesc(userId);

    	    if (latestSessionOpt.isEmpty()) {
    	        return "No active sleep session found.";
    	    }

    	    SleepSession session = latestSessionOpt.get();
    	    session.setSleepEndTime(LocalTime.now());

    	    // Calculate session duration
    	    Duration duration = Duration.between(session.getSleepStartTime(), session.getSleepEndTime());
    	    double hours = duration.toMinutes() / 60.0;
    	    session.setDurationHours(hours);

    	    // Save updated session
    	    sleepSessionRepository.save(session);

    	    // Get associated Sleep record (summary)
    	    Sleep sleep = session.getSleep();

    	    // Get all sessions for this sleep record (same day/user)
    	    List<SleepSession> allSessions = sleepSessionRepository.findBySleep(sleep);

    	    // Calculate total duration and disturbances
    	    double totalDuration = allSessions.stream()
    	            .mapToDouble(SleepSession::getDurationHours)
    	            .sum();

    	    int totalDisturbances = Math.max(0, allSessions.size() - 1); // First session doesn't count as disturbance

    	    // Update sleep summary
    	    sleep.setDurationHours(totalDuration);
    	    sleep.setSleepQuality(sleepService.estimateSleepQuality(totalDuration, totalDisturbances));

    	    sleepRepository.save(sleep);

    	    return "Sleep session ended. Total Duration: " + totalDuration + " hrs. Total Disturbances: " + totalDisturbances;
    	}
    
    @Override
    public SleepSummaryDTO getDailySummary(Long userId, LocalDate date) {
        Optional<Sleep> sleepOpt = sleepRepository.findByUserIdAndDate(userId, date);

        if (sleepOpt.isPresent()) {
            Sleep sleep = sleepOpt.get();
            List<SleepSession> sessions = sleepSessionRepository.findBySleep(sleep);

            double totalDuration = sessions.stream()
                    .mapToDouble(SleepSession::getDurationHours)
                    .sum();

            int disturbances = Math.max(0, sessions.size() - 1); // first session is normal

            return new SleepSummaryDTO(
                    sleep.getId(),
                    sleep.getDate(),
                    totalDuration,
                    disturbances,
                    sleep.getSleepQuality()
            );
        }

        return null; // Or throw custom exception like SleepDataNotFoundException
    }



}
