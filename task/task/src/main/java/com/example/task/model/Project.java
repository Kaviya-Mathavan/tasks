package com.example.task.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "project")
public class Project {
    @Id
    private String id;
    private String name;
    @DocumentReference(lazy = false)
    private User user;
    @DocumentReference(lazy = false)
    private Task task;

}