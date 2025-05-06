package com.reflecta.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reflecta.dto.SleepSummaryDTO;
import com.reflecta.entity.Goal;
import com.reflecta.entity.Sleep;
import com.reflecta.entity.SleepSession;
import com.reflecta.entity.Users;
import com.reflecta.enums.GoalStatus;
import com.reflecta.enums.GoalType;
import com.reflecta.enums.SleepQuality;
import com.reflecta.repository.GoalRepository;
import com.reflecta.repository.SleepRepository;
import com.reflecta.repository.SleepSessionRepository;
import com.reflecta.service.GoalService;
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
    
    @Autowired
    private GoalRepository goalRepository;
    
    @Autowired
    private GoalService goalService;

    // Start a new sleep session and update it in the Goal
    @Override
    public String startSleep(Long userId) {
        Users user = userService.getUserById(userId);
       
        // Fetch today's sleep record, if it exists
        LocalDate today = LocalDate.now();
        List<Sleep> sleepList = sleepRepository.findByUserIdAndDate(userId, today);
        Sleep sleep;
        boolean isDisturbance = false;

        // If there's already a sleep record for today, mark it as disturbed (multiple sessions)
        if (!sleepList.isEmpty()) {
            sleep = sleepList.get(0); // Take the first sleep record
            isDisturbance = true;
        } else {
            sleep = new Sleep();
            // If no sleep record for today, create one
            sleep.setUser(user);
            sleep.setDate(today);
            sleepRepository.save(sleep);
        }
     
        // Create and save the sleep session
        SleepSession session = new SleepSession();
        session.setUser(user);
        session.setSleepStartTime(LocalTime.now());
        session.setSleepEndTime(null);
        session.setDurationHours(0);
        session.setSleep(sleep);
        session.setDisturbances(isDisturbance ? 1 : 0); // Initial disturbance if multiple sessions

        sleepSessionRepository.save(session);
        return "Sleep session started.";
    }

    
    // End the latest sleep session
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

        // Save session
        sleepSessionRepository.save(session);

        // Update the parent Sleep record
        Sleep sleep = session.getSleep();
       
        
     // Get all sessions for this sleep record (for this day)
        //List<SleepSession> allSessions = sleepSessionRepository.findBySleep(sleep);
        List<SleepSession> allSessions = sleepSessionRepository.findByUserIdAndSleep_Date(userId, sleep.getDate());


        double totalDuration = 0.0;
        for (SleepSession s : allSessions) {
            totalDuration += s.getDurationHours();
        }

        // First session is normal, others are counted as disturbances
        int totalDisturbances = allSessions.size() > 1 ? allSessions.size() - 1 : 0;

        // Save into Sleep (daily summary)
        sleep.setDurationHours(totalDuration);
        sleep.setSleepQuality(sleepService.estimateSleepQuality(totalDuration, totalDisturbances));
        sleepRepository.save(sleep);

        // Update SLEEP goal progress if goal exists
        LocalDate today = LocalDate.now(); 
        Optional<Goal> goalOpt = goalRepository.findByUserIdAndTypeAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                userId, GoalType.SLEEP, GoalStatus.ONGOING, today, today
        );

        if (goalOpt.isPresent()) {
            Goal goal = goalOpt.get();
            List<SleepSession> todaySessions = sleepSessionRepository
                    .findByUserIdAndSleep_Date(userId, LocalDate.now());

            double todayTotal = 0.0;
            for (SleepSession s : todaySessions) {
                todayTotal += s.getDurationHours();
            }

            goalService.updateGoalProgress(goal, todayTotal);

        }

        return "Sleep session ended. Total Duration: " + totalDuration + " hrs. Total Disturbances: " + totalDisturbances;
    }


    // Updated to support multiple Sleep records for same user-date
    @Override
    public SleepSummaryDTO getDailySummary(Long userId, LocalDate date) {
        List<Sleep> sleepList = sleepRepository.findByUserIdAndDate(userId, date);

        if (sleepList.isEmpty()) {
            return null; // No data
        }

        double totalDuration = 0.0;
        int totalDisturbances = 0;

        for (Sleep sleep : sleepList) {
            List<SleepSession> sessions = sleepSessionRepository.findBySleep(sleep);
            totalDuration += sessions.stream()
                    .mapToDouble(SleepSession::getDurationHours)
                    .sum();
            totalDisturbances += Math.max(0, sessions.size() - 1); // First session is normal
        }

        SleepQuality sleepQuality = sleepService.estimateSleepQuality(totalDuration, totalDisturbances);


        return new SleepSummaryDTO(
                null, // No single sleep ID to represent all records
                date,
                totalDuration,
                totalDisturbances,
                sleepQuality
        );
    }
}
