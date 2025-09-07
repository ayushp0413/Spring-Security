package com.vector.engineering.learnspringboot.controllers;

import com.vector.engineering.learnspringboot.dto.QuoteResponseDto;
import com.vector.engineering.learnspringboot.entity.User;
import com.vector.engineering.learnspringboot.services.QuotesService;
import com.vector.engineering.learnspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private QuotesService quotesService;


    @GetMapping("/health-check")
    public String health()
    {
        return "Application running fine!";
    }

    @GetMapping
    public ResponseEntity<String> greetings() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Get the quotes for the user
        QuoteResponseDto quote = quotesService.getQuote();
        return new ResponseEntity<>("Hi " + authentication.getName() +  "\n Quote of the Day: "+ quote.getQuote(), HttpStatus.OK);
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
