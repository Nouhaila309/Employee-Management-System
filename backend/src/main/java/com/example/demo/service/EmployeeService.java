package com.example.demo.service;

import com.example.demo.DtoModel.EvaluationRatedDto;
import com.example.demo.entity.Employee;
import com.example.demo.DtoModel.ConvertEmployeeDto;
import com.example.demo.DtoModel.EmployeeDto;
import com.example.demo.entity.EvaluationRated;
import com.example.demo.entity.PerformanceEvaluation;
import com.example.demo.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(EmployeeDto employeeDto) {


        return employeeRepository.save(ConvertEmployeeDto.convertDtoToEntity(employeeDto));
    }

    public Employee updateEmployeePerformanceAndSalary(Integer employeeId, EvaluationRatedDto evaluationRatedDto) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            // Update performance evaluation
            PerformanceEvaluation newEvaluation = new PerformanceEvaluation();
            newEvaluation.setEvaluationRated(evaluationRatedDto.getEvaluationRated());
            newEvaluation.setEmployee(optionalEmployee.get());

            // Update salary based on performance rating

            double salaryMultiplier = getSalaryMultiplier(evaluationRatedDto.getEvaluationRated());
            double newSalary = optionalEmployee.get().getEmployeeSalary() * salaryMultiplier;
            double roundedSalary = Math.round(newSalary * 100) / 100.0;

            optionalEmployee.get().setEmployeeSalary(roundedSalary);
            optionalEmployee.get().setEvaluationRated(evaluationRatedDto.getEvaluationRated());
            // Save the updated employee and performance evaluation
            employeeRepository.save(optionalEmployee.get());

            return optionalEmployee.get();
        } else {
            throw new EntityNotFoundException("Employee not found with ID: " + employeeId);
        }
    }

    private double getSalaryMultiplier(EvaluationRated rating) {
        switch (rating) {
            case A:
                return 1.15;
            case B:
                return 1.10;
            case C:
                return 1.05;
            default:
                return 1.0; // For rating D or any other case
        }
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto getEmployeeById(Integer employeeId) {
        Optional<Employee> empOptional = employeeRepository.findById(employeeId);

        if (empOptional.isPresent()) {
            Employee employee = empOptional.get();
            EmployeeDto employeeDto = ConvertEmployeeDto.convertEntityToDto(employee);

            // Set the employee ID in the EmployeeDto
            employeeDto.setEmployeeId(employee.getEmployeeId());

            return employeeDto;
        } else {

            throw new EntityNotFoundException("Employee not found with ID: " + employeeId);
        }
    }

    public Map<String, Object> getSalariesById(Integer employeeId) {
        EmployeeDto employeeDto = getEmployeeById(employeeId);
        LocalDate joinDate = employeeDto.getJoinCompanyStartDate();

        List<Integer> salaries = calculateSalaries(employeeDto.getEmployeeSalary(), joinDate);

        Map<String, Object> result = new HashMap<>();
        result.put("employeeId", employeeId);
        result.put("salaries", salaries);
        result.put("message", generateMessage(employeeDto.getEmployeeSalary(), joinDate, salaries));

        log.info("The list of salaries for the employee Id: {} is: {}", employeeId, salaries);
        return result;
    }

    private List<Integer> calculateSalaries(double initialSalary, LocalDate joinDate) {
        LocalDate currentDate = LocalDate.now();
        List<Integer> salaries = new ArrayList<>();

        for (LocalDate date = joinDate; date.isBefore(currentDate); date = date.plusYears(1)) {
            double adjustedSalary = calculateAdjustedSalary(initialSalary, date);
            salaries.add((int) adjustedSalary); // Convert to Integer if needed
        }

        return salaries;
    }

    private double calculateAdjustedSalary(double currentSalary, LocalDate joinDate) {
        LocalDate currentDate = LocalDate.now();
        long yearsInCompany = ChronoUnit.YEARS.between(joinDate, currentDate);

        double adjustedSalary = currentSalary + (yearsInCompany * 500);

        return adjustedSalary;
    }

    private String generateMessage(double initialSalary, LocalDate joinDate, List<Integer> salaries) {
        StringBuilder message = new StringBuilder("The employee has a starting salary of " + (int) initialSalary +
                " at the year " + joinDate.getYear() + ". ");

        for (int i = 1; i < salaries.size(); i++) {
            int currentYear = joinDate.getYear() + i;
            message.append("At the year ").append(currentYear).append(": ").append(salaries.get(i));
            if (i < salaries.size() - 1) {
                message.append(", ");
            }
        }

        int yearsInCompany = salaries.size();
        message.append(". The employee has spent ").append(yearsInCompany).append(" year(s) in the company. The current salary is ")
                .append(salaries.get(yearsInCompany - 1)).append(".");

        return message.toString();
    }

}



































