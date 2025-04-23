package com.reflecta.service.impl;

import com.reflecta.entity.JournalEntry;
import com.reflecta.entity.Users;
import com.reflecta.repository.JournalEntryRepository;
import com.reflecta.repository.UsersRepository;
import com.reflecta.service.JournalEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JournalEntryServiceImplementation implements JournalEntryService {

    @Autowired
    private JournalEntryRepository journalRepo;

    @Autowired
    private UsersRepository usersRepo;

    @Override
    public JournalEntry addEntry(JournalEntry entry, Long userId) {
        Users user = usersRepo.findById(userId)
                              .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        entry.setUser(user);
        if (entry.getContent().length() > 255) {
            entry.setContent(entry.getContent().substring(0, 255));
        }

        return journalRepo.save(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        if (!journalRepo.existsById(id)) {
            throw new RuntimeException("Journal entry not found with ID: " + id);
        }
        journalRepo.deleteById(id);
    }

    @Override
    public List<JournalEntry> getEntriesByUser(Long userId) {
        Users user = new Users();
        user.setId(userId);
        return journalRepo.findByUser(user);
    }

    @Override
    public List<JournalEntry> getEntriesByDate(Long userId, LocalDate date) {
        Users user = new Users();
        user.setId(userId);
        return journalRepo.findByUserAndDate(user, date);
    }
}

