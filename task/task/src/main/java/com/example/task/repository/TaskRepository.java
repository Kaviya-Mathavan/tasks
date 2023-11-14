package com.example.task.repository;
import com.example.task.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task,String> {

    //    long countTasksByStatus(String status);
    @Query(value = "{'status': ?0}")

//    Task findByStatus(String status);
    List<Task> findByStatus(String status);
    @Query(value = "{'project.id': ?0}")
    List<Task> findByProject(String id);


//@Query("{'user.id': ?0}")
//Task findByUserId(String userId);
}
//    List<Task> findByStatus(String status);
//    @Query(value = "{'status': ?0}")
//    long countTasksByStatus();
//Long countTasksByStatus(String status);
//
//    long countTasksByStatus();

//    List<Task> findByStatus(Status status);
//
//    long countTasksByStatus(S);
//    List<Task> findByStatus(String status);

//    long countTasksByStatus(String status);

//    long countTasksByStatus();
//
//    long countTasksByStatus();
//    TaskService.TaskStatusCounts countTasksByStatus();


//    @Aggregation("{ $group: { _id: '$status', count: { $sum: 1 } } }")
//    List<TaskStatusCounts> countTasksByStatus();
//    @Query(value = "{'status': ?0}")
//    long countTasksByStatus(Task.Status status);
