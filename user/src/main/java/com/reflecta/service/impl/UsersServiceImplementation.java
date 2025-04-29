package com.reflecta.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reflecta.entity.ExerciseData;
import com.reflecta.entity.Users;
import com.reflecta.repository.ExerciseDataRepository;
import com.reflecta.repository.UsersRepository;
import com.reflecta.service.UsersService;

@Service
public class UsersServiceImplementation implements UsersService {

    @Autowired
    private UsersRepository userRepository;
    
    @Autowired
    private ExerciseDataRepository exerciseDataRepository;

    @Override
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    
 
    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users updateUser(Long id, Users updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setGender(updatedUser.getGender());
            user.setAge(updatedUser.getAge());
            user.setHeight(updatedUser.getHeight());
            user.setWeight(updatedUser.getWeight());
            return userRepository.save(user);
        }).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

	@Override
	public Users getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	 public double calculateBMR(Users user) {
	        if ("M".equals(user.getGender())) {
	            return 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
	        } else { // FEMALE
	            return 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
	        }
	    }
	
	@Override
	 public Double getActivityMultiplier(Users user) {
		    // Retrieve all ExerciseData for the user, assuming the ExerciseData has caloriesBurned
		    List<ExerciseData> exerciseDataList = exerciseDataRepository.findByUser(user);
		    double totalCaloriesBurned = 0;
		    LocalDate today = LocalDate.now(); // Get the current date

		    // Calculate total calories burned today
		    for (ExerciseData exercise : exerciseDataList) {
		        // Assuming ExerciseData has a method getDate() that returns the date of the exercise
		        if (exercise.getDate().equals(today)) {
		            totalCaloriesBurned += exercise.getCaloriesBurned(); // Add calories burned for today's exercise
		        }
		    }

		    // Define the activity levels based on calories burned
		    String activityLevel = "SEDENTARY"; // Default level
		    if (totalCaloriesBurned >= 200 && totalCaloriesBurned < 400) {
		        activityLevel = "LIGHTLY_ACTIVE";
		    } else if (totalCaloriesBurned >= 400 && totalCaloriesBurned < 600) {
		        activityLevel = "MODERATELY_ACTIVE";
		    } else if (totalCaloriesBurned >= 600 && totalCaloriesBurned < 800) {
		        activityLevel = "VERY_ACTIVE";
		    } else if (totalCaloriesBurned >= 800) {
		        activityLevel = "SUPER_ACTIVE";
		    }

		    // Assign multiplier based on activity level
		    double multiplier = 1.2; // Default to Sedentary if unknown
		    switch (activityLevel) {
		        case "SEDENTARY":
		            multiplier = 1.2;
		            break;
		        case "LIGHTLY_ACTIVE":
		            multiplier = 1.375;
		            break;
		        case "MODERATELY_ACTIVE":
		            multiplier = 1.55;
		            break;
		        case "VERY_ACTIVE":
		            multiplier = 1.725;
		            break;
		        case "SUPER_ACTIVE":
		            multiplier = 1.9;
		            break;
		    }

		    // Return the activity level (for now, you can return this or use it for further calculation)
		    // You could also return the multiplier here if needed for direct access
		    return multiplier;
		}


	}



