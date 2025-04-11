package com.reflecta.repository;

import com.reflecta.entity.Sleep;
import com.reflecta.entity.SleepSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SleepSessionRepository extends JpaRepository<SleepSession, Long> {

    // Find the most recent sleep session for a user
    //SleepSession findTopByUserIdOrderByIdDesc(Long userId); - replaced with Optional

    // Find all sleep sessions for a user
    List<SleepSession> findByUserId(Long userId);

    // Find sleep sessions for a user within a specific date range
    List<SleepSession> findByUserIdAndSleepStartTimeBetween(Long userId, LocalDate startDate, LocalDate endDate);
    
    // Find the most recent sleep session for a user
    Optional<SleepSession> findTopByUserIdOrderByIdDesc(Long userId);
    
    List<SleepSession> findBySleep(Sleep sleep);
    
    List<SleepSession> findByUserIdAndSleep_Date(Long userId, LocalDate date);

}
