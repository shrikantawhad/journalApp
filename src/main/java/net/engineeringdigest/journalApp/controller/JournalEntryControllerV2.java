package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/getAll")
    public List<JournalEntry> getAll() {
        return null;
    }

    @PostMapping("/createEntry")
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveJournalEntry(myEntry);

        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId) {

        return null;

    }


    @DeleteMapping("/detele/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {

        return null;

    }

    @PutMapping("/update/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry journalEntryRequest) {
        return null;

    }

}
