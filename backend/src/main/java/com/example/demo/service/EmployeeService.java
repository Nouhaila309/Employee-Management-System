package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page findAll(PageRequest pageRequest) {
        return employeeRepository.findAll(pageRequest);
    }

    public Optional<Employee> findByUsernameAndPassword(String username, String password) {
        return employeeRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    public Employee saveAndFlush(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }
}
