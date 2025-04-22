package com.reflecta.dto;

import java.time.LocalDate;

import com.reflecta.enums.SleepQuality;

public class SleepSummaryDTO {

    private Long id;
    private LocalDate date;
    private double durationHours;
    private int disturbances;
    private SleepQuality sleepQuality;

    // ✅ Correct constructor
    public SleepSummaryDTO(Long id, LocalDate date, double durationHours, int disturbances, SleepQuality sleepQuality) {
        this.id = id;
        this.date = date;
        this.durationHours = durationHours;
        this.disturbances = disturbances;
        this.sleepQuality = sleepQuality;
    }

    // Getters and Setters (or use Lombok @Data if you prefer)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        this.durationHours = durationHours;
    }

    public int getDisturbances() {
        return disturbances;
    }

    public void setDisturbances(int disturbances) {
        this.disturbances = disturbances;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(SleepQuality sleepQuality) {
        this.sleepQuality = sleepQuality;
    }
}




//package com.reflecta.dto;
//
//import java.time.LocalDate;
//
//public class SleepSummaryDTO {
//
//    private LocalDate date;
//    private double durationHours;
//    private String sleepQuality;
//    private int disturbances;
//
//    public SleepSummaryDTO(LocalDate date, double durationHours, String sleepQuality, int disturbances) {
//        this.date = date;
//        this.durationHours = durationHours;
//        this.sleepQuality = sleepQuality;
//        this.setDisturbances(disturbances);
//    }
//
//    // Getters and setters (or use Lombok if preferred)
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public double getDurationHours() {
//        return durationHours;
//    }
//
//    public void setDurationHours(double durationHours) {
//        this.durationHours = durationHours;
//    }
//
//    public String getSleepQuality() {
//        return sleepQuality;
//    }
//
//    public void setSleepQuality(String sleepQuality) {
//        this.sleepQuality = sleepQuality;
//    }
//
//	public int getDisturbances() {
//		return disturbances;
//	}
//
//	public void setDisturbances(int disturbances) {
//		this.disturbances = disturbances;
//	}
//}
//
