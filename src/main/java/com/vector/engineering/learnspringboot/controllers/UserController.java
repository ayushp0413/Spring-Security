package com.vector.engineering.learnspringboot.controllers;

import com.vector.engineering.learnspringboot.entity.User;
import com.vector.engineering.learnspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/learn")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String health()
    {
        return "Application running fine!";
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User dbUSer = userService.findByUsername(username);
        dbUSer.setUsername(user.getUsername());
        dbUSer.setPassword(user.getPassword());
        userService.addUser(dbUSer); // with password encoding
        dbUSer.setPassword(null);
        return ResponseEntity.ok(dbUSer);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUsername(authentication.getName());
        return ResponseEntity.status(200).body("Deleted Successfully");
    }


}
