package com.vector.engineering.learnspringboot.controllers;

import com.vector.engineering.learnspringboot.entity.ConfigJournalApp;
import com.vector.engineering.learnspringboot.entity.User;
import com.vector.engineering.learnspringboot.repository.ConfigJournalAppRepository;
import com.vector.engineering.learnspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String health()
    {
        return "Application running fine!";
    }

    // testing purpose
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user)
    {
        userService.addUser(user);
        return ResponseEntity.ok("USER Added");
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUser());
    }


}
