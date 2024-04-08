package com.example.demo.repository;

import com.example.demo.entity.ProjectEmployee;
import com.example.demo.entity.ProjectEmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployee, ProjectEmployeeId>, JpaSpecificationExecutor<ProjectEmployee> {

}
