package com.reflecta.controller;

import com.reflecta.entity.JournalEntry;
import com.reflecta.service.JournalEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/journal")
@CrossOrigin("*") // optional, helpful if you're testing with frontend like React
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalService;

    // Add a journal entry for a specific user
    @PostMapping("/add/{userId}")
    public JournalEntry addEntry(@RequestBody JournalEntry entry, @PathVariable Long userId) {
        return journalService.addEntry(entry, userId);
    }

    // Delete a journal entry by its ID
    @DeleteMapping("/delete/{entryId}")
    public String deleteEntry(@PathVariable Long entryId) {
        journalService.deleteEntry(entryId);
        return "Entry deleted successfully.";
    }

    // Get all entries by user
    @GetMapping("/user/{userId}")
    public List<JournalEntry> getEntriesByUser(@PathVariable Long userId) {
        return journalService.getEntriesByUser(userId);
    }

    // Get entries by user and date
    @GetMapping("/user/{userId}/date")
    public List<JournalEntry> getEntriesByDate(
            @PathVariable Long userId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return journalService.getEntriesByDate(userId, date);
    }
}
