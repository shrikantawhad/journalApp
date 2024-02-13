package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<JournalEntry> getAll() {
       return journalEntryService.getAllJounalEntry();
    }

    @PostMapping("/createEntry")
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveJournalEntry(myEntry);

        return myEntry;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {

        return journalEntryService.getJounalEntryById(myId).orElse(null);

    }


    @DeleteMapping("/detele/id/{myId}")
    public ObjectId deleteJournalEntryById(@PathVariable ObjectId myId) {

       journalEntryService.deleteJounalEntryById(myId);
       return myId;

    }

    @PutMapping("/update/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newJournalEntryRequest) {
            JournalEntry oldJournalEntry = journalEntryService.getJounalEntryById(myId).orElse(null);

            if(oldJournalEntry != null){
                oldJournalEntry.setTitle(newJournalEntryRequest.getTitle() != null && !newJournalEntryRequest.getTitle().equals("") ? newJournalEntryRequest.getTitle():oldJournalEntry.getTitle());
                oldJournalEntry.setContent(newJournalEntryRequest.getContent() != null && !newJournalEntryRequest.getContent().equals("") ? newJournalEntryRequest.getContent(): oldJournalEntry.getContent());
            }
            journalEntryService.saveJournalEntry(oldJournalEntry);

        return oldJournalEntry;
    }

}
