package com.example.task.controller;

//import com.example.task.dto.AuthRequest;
import com.example.task.model.*;
//import com.example.task.model.TaskStatusCounts;
//import com.example.task.service.JwtService;
import com.example.task.repository.TaskRepository;
import com.example.task.service.JwtService;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private final TaskService taskService;
    private TaskRepository taskRepository;

    @Autowired
    private TaskService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask , HttpStatus.CREATED);
    }


    //
//        @GetMapping("/count")
//    public ResponseEntity<Map<String, Long>> getTaskCounts() {
//        Map<String, Long> countsMap = new HashMap<>();
//        countsMap.put("Total_Tasks",taskService.getTotalTasks(Status.valueOf("Total_Tasks")));
//        countsMap.put("TO_DO", taskService.getTasksCountByStatus(String.valueOf(Status.valueOf("TO_Do"))));
//        countsMap.put("IN_PROGRESS", taskService.getTasksCountByStatus(String.valueOf(Status.valueOf("IN_PROGRESS"))));
//        countsMap.put("COMPLETED", taskService.getTasksCountByStatus(String.valueOf(Status.valueOf("COMPLETED"))));
//        return ResponseEntity.ok(countsMap);
//    }

    @GetMapping("/status-counts")
    public ResponseEntity<List<Status>> getTasksByStatus() {
//        List<Task> tasks = (List<Task>) taskRepository.findByStatus(status);
        return ResponseEntity.ok(taskService.getTaskStatusCounts());
    }

    @GetMapping("/count/project/{id}")
    public ResponseEntity<List<Status>> getTaskCountByProjectId(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(taskService.getTaskCountProjectId(id));
    }

    @PostMapping("/assign/{userId}/{projectID}")
    public ResponseEntity<Task> assignTask(@PathVariable String userId , @PathVariable String projectId) {
        return ResponseEntity.ok(taskService.assignTask(userId , projectId));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
//
//    @PostMapping("/new")
//    public String addNewUser(@RequestBody UserInfo userInfo) {
//        return service.addUser(userInfo);
//    }
//
//    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public List<User> getAllTheProducts() {
//        return service.getProducts();
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public Product getProductById(@PathVariable int id) {
//        return service.getProduct(id);
//    }

    @PostMapping("/userinfo")
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo) {
        UserInfo createdUser= taskService.createUser(userInfo);

        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
//    public void registerUser(User user) {
//        // Encode the user's password before storing it in the database
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName() , authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            System.out.println(authentication);
            return jwtService.generateToken(authRequest.getUserName());

        } else {
            System.out.println(authentication);
            throw new UsernameNotFoundException("invalid user request !");
        }

    }
}

