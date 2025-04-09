package com.reflecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflecta.entity.JournalEntry;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
	
}
