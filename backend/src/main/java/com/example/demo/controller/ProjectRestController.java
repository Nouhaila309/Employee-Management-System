package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.DtoModel.ProjectDTO;
import com.example.demo.DtoModel.ProjectEmployeeIdDTO;
import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectEmployee;
import com.example.demo.entity.ProjectEmployeeId;
import com.example.demo.service.ProjectEmployeeService;
import com.example.demo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {

    private final ProjectService projectService;

    private final ProjectEmployeeService projectEmployeeService;

    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("")
    public ResponseEntity<Page<Project>> getProjectsPaginated(
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "pageIndex", defaultValue = "0", required = false) Integer pageIndex) {
        return new ResponseEntity<>(projectService.findAll(PageRequest.of(pageIndex, pageSize)), HttpStatus.OK);
    }

    @GetMapping("check/{projectId}/employee/{employeeId}")
    public ResponseEntity<Optional<ProjectEmployee>> checkProjectEmployee(@PathVariable("projectId") Integer projectId,
                                                                          @PathVariable("employeeId") String employeeId) {
        return new ResponseEntity<>(projectEmployeeService.findById(new ProjectEmployeeId(projectId, employeeId)),
                HttpStatus.OK);
    }

    @PostMapping("/project-employee")
    public ResponseEntity<Object> addProjectEmployee(@RequestBody ProjectEmployeeIdDTO projectEmployeeId) {
        ProjectEmployeeId id = mapper.map(projectEmployeeId, ProjectEmployeeId.class);
        projectEmployeeService.save(new ProjectEmployee(id));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> getProjectById(@PathVariable("id") int id) {
        return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveProject(@RequestBody ProjectDTO project) {
        return new ResponseEntity<>(projectService.saveAndFlush(mapper.map(project, Project.class)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProjectById(@PathVariable("id") int id) {
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}