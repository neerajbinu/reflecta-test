package com.reflecta.service;

import com.reflecta.entity.JournalEntry;

import java.time.LocalDate;
import java.util.List;

public interface JournalEntryService {
    JournalEntry addEntry(JournalEntry entry, Long userId);
    void deleteEntry(Long id);
    List<JournalEntry> getEntriesByUser(Long userId);
    List<JournalEntry> getEntriesByDate(Long userId, LocalDate date);
}
