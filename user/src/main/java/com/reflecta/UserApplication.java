package com.reflecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling

public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}


@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}

}
	

		
/*
 * Test Code
 
	@Bean
    @Transactional
    CommandLineRunner run(
        UsersRepository userRepository,
        GoalRepository goalRepository,
        ExerciseDataRepository exerciseRepository,
        DietRepository dietRepository,
        SleepRepository sleepRepository,
        MoodRepository moodRepository
    ) {
        return args -> {
            // 1. Create and save a user
            Users user = new Users();
            user.setName("Reflecta User");
            user.setEmail("reflecta@wellness.com");
            user.setAge(28);
            user.setGender("Female");
            user.setHeight(160);
            user.setWeight(55);
            user.setDateOfBirth(LocalDate.of(1996, 4, 1));
            userRepository.save(user);
            
            // 2. Add Goal
            Goal goal = new Goal();
            goal.setType(GoalType.MEDITATION);
            //goal.setDescription("Meditate 10 mins daily");
            goal.setFrequency(Frequency.DAILY);
            goal.setUser(user);
            goalRepository.save(goal);
            
            // 3. Add Exercise
            ExerciseData exercise = new ExerciseData();
            exercise.setDate(null);
            exercise.setExerciseType("Walking");
            exercise.setDurationMinutes(30);
            exercise.setCaloriesBurned(300);
            exercise.setUser(user);
            exerciseRepository.save(exercise);
            
            // 4. Add Diet
            Diet diet = new Diet();
            diet.setMealType("Breakfast");
            diet.setTotalCalories(500);
            diet.setUser(user);
            dietRepository.save(diet);
            
            // 5. Add Sleep
            Sleep sleep = new Sleep();
            sleep.setDurationHours(8);
            //sleep.setQuality("Good");
            sleep.setUser(user);
            sleepRepository.save(sleep);
            
            // 6. Add Mood
            Mood mood = new Mood();
            mood.setMoodStatus("Happy");
           // mood.setDescription("Had a productive day!");
            mood.setUser(user);
            moodRepository.save(mood);
            
            // 7. Print to console - safer approach to avoid LazyInitializationException
            System.out.println("==== USER AND ASSOCIATED DATA ====");
            System.out.println("User: " + user.getName() + " (ID: " + user.getId() + ")");
            System.out.println("Goals: " + goalRepository.findAll().size() + " entries");
            System.out.println("Exercises: " + exerciseRepository.findAll().size() + " entries");
            System.out.println("Diets: " + dietRepository.findAll().size() + " entries");
            System.out.println("Sleep: " + sleepRepository.findAll().size() + " entries");
            System.out.println("Moods: " + moodRepository.findAll().size() + " entries");
        };
    }
}

*/
	



