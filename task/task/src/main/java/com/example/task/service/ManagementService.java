package com.example.task.service;

import com.example.task.model.*;
import com.example.task.repository.ManagementRepository;
import com.example.task.repository.ProjectRepository;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;

@Autowired
    ManagementRepository managementRepository;

    @Autowired
    public ManagementService(ManagementRepository managementRepository) {
        this.managementRepository=managementRepository;
    }

    public Management assignTask(String userId, String taskId){
        Optional<Task> task=taskRepository.findById(taskId);
        Optional<User> user= userRepository.findById(userId);

        Status initialStatus=Status.TO_Do;
       Management task1=new Management();
       task1.setUser(user.get());
       task1.setProject(task.get().getProject());
       task1.setTask(task.get());
       task1.setStatus(initialStatus);
        return managementRepository.save(task1);

    }
    public List<Management>getAllTaskDetails(){
        return managementRepository.findAll();
    }

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
    public Management statusUpdate(String taskId,Management status){
        Management management=managementRepository.findByTaskId(taskId);
        management.setStatus(status.getStatus());
        return managementRepository.save(management);
    }

    public List<Status> getTaskCountByUserId(String id) {
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
        AggregationResults<Status> results = mongoTemplate.aggregate(aggregation, "user", Status.class);
        System.out.println(results.getRawResults());
        List<Status> output = results.getMappedResults();
        return output;
    }
}
