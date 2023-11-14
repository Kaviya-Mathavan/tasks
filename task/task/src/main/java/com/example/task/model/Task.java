package com.example.task.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "task")
    public class Task {


    @Id
    private String id;
    private Status status;

        private String title;
        private String description;
        private LocalDate dueDate;

        private String projectId;
        @DocumentReference(lazy = false)
        private User user;
        @DocumentReference(lazy = false)
        private Project project;


    public void setStatus(com.example.task.model.Status initialStatus) {
    }
//        @Field("status")
//        private Status status;

//    private long count;
//    private long totalCompletedTasks;

  public enum Status {
        TO_Do,
        IN_PROGRESS,
        COMPLETED
    }
}

