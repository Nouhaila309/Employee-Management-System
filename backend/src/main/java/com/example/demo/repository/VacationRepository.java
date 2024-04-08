package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer>, JpaSpecificationExecutor<Vacation> {
	
	List<Vacation> findAllByEmployee(Employee employee);
}