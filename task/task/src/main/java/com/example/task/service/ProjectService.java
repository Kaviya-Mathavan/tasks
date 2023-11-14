package com.example.task.service;

import com.example.task.exception.ResourceNotFoundException;
import com.example.task.model.Project;
import com.example.task.model.Task;
import com.example.task.model.User;
import com.example.task.repository.ProjectRepository;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {

    Project createProject(String id,Project project);

    //    @Override
    //    public Project createProject(Project project) {
    //
    //        return projectRepository.save(project);
    //    }
  //  Project createProject(String id, Project project);
}