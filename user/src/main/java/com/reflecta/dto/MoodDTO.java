package com.reflecta.dto;

import com.reflecta.enums.MoodStatus;
import java.time.LocalDate;

public class MoodDTO {
    private MoodStatus moodStatus;  // User's mood (from the enum)
         
    // --- Getters and Setters ---

    public MoodStatus getMoodStatus() {
        return moodStatus;
    }

    public void setMoodStatus(MoodStatus moodStatus) {
        this.moodStatus = moodStatus;
    }

 
}
