package com.example.task.service;
import com.example.task.model.*;
import com.example.task.repository.ProjectRepository;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserInfoRepository;
import com.example.task.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public UserInfo createUser(UserInfo userInfo) {
        String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encodedPassword);
        return userInfoRepository.save(userInfo);
    }
//    public AuthRequest createUser(AuthRequest authRequest) {
//        return userInfoRepository.save(authRequest);
//    }

//    public long getTotalTasks(Status totalTasks) {
//        return taskRepository.findAll().size();
//    }
//
//    public long getTasksCountByStatus(String status) {
//        return taskRepository.countTasksByStatus(status);
//    }


    public List<Status> getTaskStatusCounts() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group()
                        .count().as("totalTasks")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("COMPLETED")).then(1).otherwise(0)).as("completedTasks")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("TO_Do")).then(1).otherwise(0)).as("todoTasks")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("IN_PROGRESS")).then(1).otherwise(0)).as("inProgressTasks")
        );
        System.out.println(aggregation);
        AggregationResults<Status> results = mongoTemplate.aggregate(aggregation, "task", Status.class);
        System.out.println(results.getRawResults());
        List<Status> output = results.getMappedResults();
        return output;
}
    public List<Status> getTaskCountProjectId(String id) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("id").is(new ObjectId(id))),
                Aggregation.group()
                        .count().as("totalTasks")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("COMPLETED")).then(1).otherwise(0)).as("completedTasks")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("TO_Do")).then(1).otherwise(0)).as("todoTasks")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("IN_PROGRESS")).then(1).otherwise(0)).as("inProgressTasks")
        );
        System.out.println(aggregation);
        AggregationResults<Status> results = mongoTemplate.aggregate(aggregation, "project", Status.class);
        System.out.println(results.getRawResults());
        List<Status> output = results.getMappedResults();
        return output;
    }
    public Task assignTask(String userId,String ProjectId){
        Optional<User> user= userRepository.findById(userId);
        Optional<Project> project= projectRepository.findById(ProjectId);
         Status initialStatus=Status.TO_Do;
        Task user1=new Task();
        user1.setProject(project.get());
        user1.setUser(user.get());
        user1.setStatus(initialStatus);
        return taskRepository.save(user1);

    }
}

