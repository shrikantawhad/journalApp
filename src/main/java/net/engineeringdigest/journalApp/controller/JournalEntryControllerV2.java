package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JounalEntryRepository;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;




    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<?> allEntries = journalEntryService.getAllJounalEntry();


        if (allEntries != null && !allEntries.isEmpty()) {
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createEntry")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
           // myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveJournalEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {

        Optional<JournalEntry> journalEntry = journalEntryService.getJounalEntryById(myId);

//        if(journalEntry.isPresent()) {
//            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//        }
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // Lambda Expression
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @DeleteMapping("/detele/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {

        journalEntryService.deleteJounalEntryById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newJournalEntryRequest) {
        JournalEntry oldJournalEntry = journalEntryService.getJounalEntryById(myId).orElse(null);


        if (oldJournalEntry != null) {
            oldJournalEntry.setTitle(newJournalEntryRequest.getTitle() != null && !newJournalEntryRequest.getTitle().equals("") ? newJournalEntryRequest.getTitle() : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(newJournalEntryRequest.getContent() != null && !newJournalEntryRequest.getContent().equals("") ? newJournalEntryRequest.getContent() : oldJournalEntry.getContent());
            journalEntryService.saveJournalEntry(oldJournalEntry);
            return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
