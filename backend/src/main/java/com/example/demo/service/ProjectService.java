package com.example.demo.service;

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectEmployee;
import com.example.demo.entity.ProjectEmployeeId;
import com.example.demo.repository.ProjectEmployeeRepository;
import com.example.demo.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectEmployeeRepository projectEmployeeRepository;
    private final ModelMapper mapper = new ModelMapper();

    public Page<Project> findAll(PageRequest pageRequest) {
        return projectRepository.findAll(pageRequest);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(int id) {
        return projectRepository.findById(id);
    }

    public Optional<ProjectEmployee> checkProjectEmployee(Integer projectId, String employeeId) {
        return projectEmployeeRepository.findById(new ProjectEmployeeId(projectId, employeeId));
    }

    public void addProjectEmployee(ProjectEmployeeId projectEmployeeId) {
        projectEmployeeRepository.save(new ProjectEmployee(projectEmployeeId));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveAndFlush(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    public void deleteById(int id) {
        projectRepository.deleteById(id);
    }
}
