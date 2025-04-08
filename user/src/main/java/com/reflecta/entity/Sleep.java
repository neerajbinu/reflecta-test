package com.reflecta.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;



@Entity
@Data
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime sleepStartTime;
    private LocalTime sleepEndTime;
    private double durationHours;

    @ManyToOne
    private Users user;
}
