package com.example.task.controller;
import com.example.task.model.User;

import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
////@RequestMapping("/api/users")
//public class UserController{
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        userService.createUser(user);
//        return ResponseEntity.ok("User registered successfully!");
//    }
//    @PostMapping("/user/{id}")
//    public ResponseEntity<User> createUser1(@PathVariable(value="id") String id, @RequestBody User user) {
//        User createdUser1 = userService.createUser1(user);
//        return new ResponseEntity<>(createdUser1, HttpStatus.CREATED);
//    }
//}
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
       User createdUser= userService.createUser(user);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

}