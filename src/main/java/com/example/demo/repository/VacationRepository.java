package com.example.demo.repository;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {
  List<Vacation> findAllByEmployee(Employee employee);

}