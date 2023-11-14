package com.example.task.service;

import com.example.task.model.Project;
import com.example.task.model.Status;
import com.example.task.model.User;
import com.example.task.repository.ProjectRepository;
import com.example.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Project createProject(Project project) {
//
//        return projectRepository.save(project);
//    }
@Override
    public Project createProject(String id, Project project) {
        Optional<User> user = userRepository.findById(id);
        Project project1=new Project();
        project1.setName(project.getName());
        project1.setUser(user.get());
       return projectRepository.save(project1);

    }

}