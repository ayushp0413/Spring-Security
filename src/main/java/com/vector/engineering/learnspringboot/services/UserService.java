package com.vector.engineering.learnspringboot.services;

import com.vector.engineering.learnspringboot.entity.User;
import com.vector.engineering.learnspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void addUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // for spring security
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public void saveUserWithoutPassEncode(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username)
    {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.get();
    }

    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }


    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

}
