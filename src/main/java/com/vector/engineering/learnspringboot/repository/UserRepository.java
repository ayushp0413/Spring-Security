package com.vector.engineering.learnspringboot.repository;


import com.vector.engineering.learnspringboot.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
