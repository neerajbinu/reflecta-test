package com.reflecta.service.impl ;

import com.reflecta.dto.MoodDTO;
import com.reflecta.entity.Mood;
import com.reflecta.entity.Users;
import com.reflecta.repository.MoodRepository;
import com.reflecta.repository.UsersRepository;
import com.reflecta.service.MoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MoodServiceImplementation implements MoodService {

    @Autowired
    private MoodRepository moodRepository;
    
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Mood saveMood(MoodDTO moodDTO, Long userId) {
        // Retrieve the user entity from the database using the userId
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new Mood object from the DTO
        Mood mood = new Mood();
        mood.setMoodStatus(moodDTO.getMoodStatus());
        mood.setDate(LocalDate.now()); // Set current date if none provided
        mood.setUser(user); // Associate the user with the mood entry
        
        // Calculate the mood score based on the MoodStatus
        int moodScore = mood.getMoodStatus().getScore();
        mood.setStressLevel(moodScore);
        
        // Optionally, you can store this score in the Mood entity or use it for further calculations.
        // For now, we will simply log or return it.
        System.out.println("Mood Score: " + moodScore);

        // Save the mood entry to the database
        return moodRepository.save(mood);
    }
    
    @Override
    public double calculateWeeklyAverageMoodScore(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(7);

        List<Mood> weeklyMoods = moodRepository.findByUserIdAndDateBetween(userId, weekAgo, today);

        if (weeklyMoods.isEmpty()) return 0;

        double totalScore = 0;
        for (Mood mood : weeklyMoods) {
            totalScore += mood.getMoodStatus().getScore();
        }

        return totalScore / weeklyMoods.size();
    }
    
    @Override
    public List<Mood> getMoodsForPastWeek(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minusDays(6); // inclusive of today, 7 days total

        return moodRepository.findByUserIdAndDateBetween(userId, oneWeekAgo, today);
    }


}
