package com.vector.engineering.learnspringboot.controllers;

import com.vector.engineering.learnspringboot.entity.User;
import com.vector.engineering.learnspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user)
    {
        userService.addUser(user);
        return ResponseEntity.ok("USER Added");
    }

}
