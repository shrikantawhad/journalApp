package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    public HashMap<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/getAll")
    public List<JournalEntry> getAll() {

//        return (List<JournalEntry>) journalEntries.values();

        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping("/createEntry")
    public boolean createEntry(@RequestBody JournalEntry myEntry) {

//        return (List<JournalEntry>) journalEntries.values();
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId) {

        return journalEntries.get(myId);

    }


    @DeleteMapping("/detele/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {

        return journalEntries.remove(myId);

    }

    @PutMapping("/update/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry journalEntryRequest) {

        return journalEntries.replace(myId, journalEntryRequest);

    }

}
