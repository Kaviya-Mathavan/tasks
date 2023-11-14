package com.example.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//public enum Status {
//    Total_Tasks,
//    TO_Do,
//    IN_PROGRESS,
//    COMPLETED
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Status {

    public static Status TO_Do;
    private long totalTasks;
private long todoTasks;
private long inProgressTasks;
private long completedTasks;

}
