package com.example.task.repository;
import com.example.task.model.AuthRequest;
import com.example.task.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByName(String username);

}