package com.example.task.repository;
import com.example.task.model.Project;
import com.example.task.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

//    @Query(value = "{'project.id': ?0}")
//    List<Task> findByProject(String id);

}