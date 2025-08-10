package com.vector.engineering.learnspringboot.repository;

import com.vector.engineering.learnspringboot.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId> {

}
