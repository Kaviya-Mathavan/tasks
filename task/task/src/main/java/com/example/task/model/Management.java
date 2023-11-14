package com.example.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "management")
public class Management {
    @Id
    private String id;
    @DocumentReference(lazy = false)
    private User user;
    @DocumentReference(lazy = false)
    private Project project;
    @DocumentReference(lazy = false)
    private Task task;
    @Field("status")
    private Status status;
}
