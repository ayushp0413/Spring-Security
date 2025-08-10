package com.vector.engineering.learnspringboot.controllers;


import com.vector.engineering.learnspringboot.entity.JournalEntry;
import com.vector.engineering.learnspringboot.entity.User;
import com.vector.engineering.learnspringboot.repository.JournalRepository;
import com.vector.engineering.learnspringboot.services.JournalService;
import com.vector.engineering.learnspringboot.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.JobHoldUntil;
import javax.sql.rowset.serial.SerialClob;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        List<JournalEntry> entries = user.getJournalEntries();
        System.out.println(entries);

        if(entries!=null && !entries.isEmpty())
        {
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        return new ResponseEntity<>(entries, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry)
    {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            journalService.createEntry(journalEntry, authentication.getName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            System.out.println("Unable to create user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        Optional<JournalEntry> entry = journalService.getById(user, id);

        if(entry.isEmpty())
        {
            return new ResponseEntity<>(entry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }


    // update, delete etc...


}
