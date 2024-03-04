package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JounalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JounalEntryRepository journalEntryRepository;


    public void saveJournalEntry(JournalEntry journalEntry) {

        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
        }catch (Exception e){
            log.error("Exception ", e);
        }

    }


    public List<JournalEntry> getAllJounalEntry() {
        return journalEntryRepository.findAll();
    }


    public Optional<JournalEntry> getJounalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteJounalEntryById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

}
