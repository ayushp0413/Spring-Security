package com.vector.engineering.learnspringboot.services;

import com.vector.engineering.learnspringboot.entity.JournalEntry;
import com.vector.engineering.learnspringboot.entity.User;
import com.vector.engineering.learnspringboot.repository.JournalRepository;
import com.vector.engineering.learnspringboot.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void createEntry(JournalEntry journalEntry, String username)
    {
        Optional<User> user = userRepository.findByUsername(username);
        // 100% present, ye context se mila hai
        journalEntry.setCreatedDate(LocalDate.now());
        journalRepository.save(journalEntry);
        user.get().getJournalEntries().add(journalEntry);
        userService.saveUserWithoutPassEncode(user.get());
    }

    public Optional<JournalEntry>getById(User user, ObjectId id)
    {
        // check this Journal id is present in user of not
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(e -> e.getId().equals(id)).toList();

        if(!collect.isEmpty())
        {
            Optional<JournalEntry> entry = journalRepository.findById(id);
            return entry;
        }
        return Optional.empty();
    }

}
