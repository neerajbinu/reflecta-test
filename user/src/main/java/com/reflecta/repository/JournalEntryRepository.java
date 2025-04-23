package com.reflecta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.JournalEntry;
import com.reflecta.entity.Users;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
	List<JournalEntry>findByUser(Users user);
	List<JournalEntry>findByUserAndDate(Users user,LocalDate date);
	
}
