package com.example.task.repository;

import com.example.task.model.Management;
import com.example.task.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementRepository extends MongoRepository<Management,String> {
@Query("{'task.id':?0}")
    Management findByTaskId(String taskId);
}
