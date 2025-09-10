package com.vector.engineering.learnspringboot.controllers;

import com.vector.engineering.learnspringboot.cache.AppCache;
import com.vector.engineering.learnspringboot.entity.ConfigJournalApp;
import com.vector.engineering.learnspringboot.entity.User;
import com.vector.engineering.learnspringboot.repository.ConfigJournalAppRepository;
import com.vector.engineering.learnspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("all-users")
    public ResponseEntity<?> allUsers()
    {

        List<User> allUser = userService.getAllUser();

        if(allUser!=null && !allUser.isEmpty())
        {
            return new ResponseEntity<>(allUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(allUser, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache()
    {
        appCache.init();    // it recreates the map and loads the data from DB
    }

}
