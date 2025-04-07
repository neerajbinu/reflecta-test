package com.reflecta.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class User {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private int age;
    private String gender;
    private double height;
    private double weight;

    @OneToMany(mappedBy = "user")
    private List<ExerciseData> exerciseDataList;

    @OneToMany(mappedBy = "user")
    private List<Diet> dietList;

    @OneToMany(mappedBy = "user")
    private List<Sleep> sleepList;

    @OneToMany(mappedBy = "user")
    private List<Mood> moods;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JournalEntry> journalEntryList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MentalHealthAlert> mentalHealthAlertList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LongtermGoal> longtermGoalList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DailyGoal> dailyGoalList;
    
    

    //empty classes created only to test the User Entity

public class Diet {
    // Placeholder class for now
}

public class Sleep {
    // Placeholder class for now
}

public class Mood {
    // Placeholder class for now
}

public class Goal {
    // Placeholder class for now
}

public class ExerciseData {
    // Placeholder class for now
}
public class LongtermGoal{
    // Placeholder class for now
}

public class DailyGoal {
    // Placeholder class for now
}

public class MentalHealthAlert {
    // Placeholder class for now
}

public class JournalEntry {
    // Placeholder class for now
}




}
