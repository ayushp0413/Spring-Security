package com.vector.engineering.learnspringboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document(collation = "journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;

    private String title;

    private String description;

    private LocalDate createdDate;


}
