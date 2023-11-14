package com.example.task.controller;

import com.example.task.model.Management;
import com.example.task.model.Status;
import com.example.task.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class ManagementController{
    @Autowired
    ManagementService managementService;
    @GetMapping("/task")
    public ResponseEntity<List<Management>> getTaskDetails()
    {
        return ResponseEntity.ok(managementService.getAllTaskDetails());
    }
    @PostMapping("/add/{userId}/{taskId}")
    public ResponseEntity<Management> assignTask(@PathVariable String userId,@PathVariable String taskId){
        return ResponseEntity.ok(managementService.assignTask(userId, taskId));
    }
    @GetMapping("/status-counts")
    public ResponseEntity<List<Status>> getTasksByStatus() {
//        List<Task> tasks = (List<Task>) taskRepository.findByStatus(status);
        return ResponseEntity.ok(managementService.getTaskStatusCounts());
    }
    @GetMapping("/count/project/{id}")
    public ResponseEntity<List<Status>> getTaskCountByProjectId(@PathVariable (value = "id")String id) {
        return ResponseEntity.ok(managementService.getTaskCountProjectId(id));
    }
    @PutMapping("/status/update/{taskId}")
    public ResponseEntity<Management> assignTask(@PathVariable String taskId,@RequestBody Management status){
        return ResponseEntity.ok(managementService.statusUpdate(taskId, status));
    }
    @GetMapping("/count/user/{userid}")
    public ResponseEntity<Management> getTaskCountByUserId(@PathVariable (value = "id")String id){
        return ResponseEntity.ok((Management) managementService.getTaskCountByUserId(id));
    }
}
