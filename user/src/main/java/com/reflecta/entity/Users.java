package com.reflecta.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
//@Data
@Table(name = "users") 
public class Users {
    
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
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public List<ExerciseData> getExerciseDataList() {
		return exerciseDataList;
	}

	public void setExerciseDataList(List<ExerciseData> exerciseDataList) {
		this.exerciseDataList = exerciseDataList;
	}

	public List<Diet> getDietList() {
		return dietList;
	}

	public void setDietList(List<Diet> dietList) {
		this.dietList = dietList;
	}

	public List<Sleep> getSleepList() {
		return sleepList;
	}

	public void setSleepList(List<Sleep> sleepList) {
		this.sleepList = sleepList;
	}

	public List<Mood> getMoods() {
		return moods;
	}

	public void setMoods(List<Mood> moods) {
		this.moods = moods;
	}

	public List<JournalEntry> getJournalEntryList() {
		return journalEntryList;
	}

	public void setJournalEntryList(List<JournalEntry> journalEntryList) {
		this.journalEntryList = journalEntryList;
	}

	public List<MentalHealthAlert> getMentalHealthAlertList() {
		return mentalHealthAlertList;
	}

	public void setMentalHealthAlertList(List<MentalHealthAlert> mentalHealthAlertList) {
		this.mentalHealthAlertList = mentalHealthAlertList;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	
	 public List<WaterIntake> getWaterIntakeList() {
			return waterIntakeList;
		}

	public void setWaterIntakeList(List<WaterIntake> waterIntakeList) {
			this.waterIntakeList = waterIntakeList;
		}
	
	
	
	@Override
	public String toString() {
	    return "Users [id=" + id + ", name=" + name + ", email=" + email + ", dateOfBirth=" + dateOfBirth + 
	           ", age=" + age + ", gender=" + gender + ", height=" + height + ", weight=" + weight + "]";
	}
	
	
	
//	@Override -- commented out because accessing lazy in toString() won't give the output as the transaction will be closed
//	public String toString() {
//		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", age="
//				+ age + ", gender=" + gender + ", height=" + height + ", weight=" + weight + ", exerciseDataList="
//				+ exerciseDataList + ", dietList=" + dietList + ", sleepList=" + sleepList + ", moods=" + moods
//				+ ", journalEntryList=" + journalEntryList + ", mentalHealthAlertList=" + mentalHealthAlertList
//				+ ", goals=" + goals + "]";
//	}
	

	public Users(Long id, String name, String email, LocalDate dateOfBirth, int age, String gender, double height,
			double weight, List<ExerciseData> exerciseDataList, List<Diet> dietList, List<Sleep> sleepList,
			List<Mood> moods, List<JournalEntry> journalEntryList, List<MentalHealthAlert> mentalHealthAlertList,
			List<Goal> goals, List<WaterIntake> waterIntakeList) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.exerciseDataList = exerciseDataList;
		this.dietList = dietList;
		this.sleepList = sleepList;
		this.moods = moods;
		this.journalEntryList = journalEntryList;
		this.mentalHealthAlertList = mentalHealthAlertList;
		this.goals = goals;
		this.waterIntakeList = waterIntakeList;
	}
	
	public Users() {}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExerciseData> exerciseDataList;


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diet> dietList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sleep> sleepList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mood> moods;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JournalEntry> journalEntryList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MentalHealthAlert> mentalHealthAlertList;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WaterIntake> waterIntakeList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goals;

}
