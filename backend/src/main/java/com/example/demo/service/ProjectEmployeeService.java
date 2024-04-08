package com.example.demo.service;

import com.example.demo.entity.ProjectEmployee;
import com.example.demo.entity.ProjectEmployeeId;
import com.example.demo.repository.ProjectEmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectEmployeeService {

    private final ProjectEmployeeRepository projectEmployeeRepository;

    public void save(ProjectEmployee projectEmployee) {
        projectEmployeeRepository.save(projectEmployee);
    }

    public Optional<ProjectEmployee> findById(ProjectEmployeeId projectEmployeeId) {
        return projectEmployeeRepository.findById(projectEmployeeId);
    }
}
