package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JounalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryService {

    @Autowired
    private JounalEntryRepository journalEntryRepository;


    public void saveJournalEntry(JournalEntry journalEntry) {

        journalEntryRepository.save(journalEntry);
    }

}
