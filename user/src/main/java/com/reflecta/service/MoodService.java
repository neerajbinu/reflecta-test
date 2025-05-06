package com.reflecta.service;

import java.util.List;

import com.reflecta.dto.MoodDTO;
import com.reflecta.entity.Mood;

public interface MoodService {

	Mood saveMood(MoodDTO moodDTO, Long userId);

	double calculateWeeklyAverageMoodScore(Long userId);

	List<Mood> getMoodsForPastWeek(Long userId);

}
