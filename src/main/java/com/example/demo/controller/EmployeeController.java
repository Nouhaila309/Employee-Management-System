package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.DtoModel.ConvertEmployeeDto;
import com.example.demo.DtoModel.EmployeeDto;
import com.example.demo.DtoModel.EvaluationRatedDto;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EvaluationRated;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService empService;

    @PostMapping("/saveEmployee")
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto employee) {
        return ConvertEmployeeDto.convertEntityToDto(empService.saveEmployee(employee));
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee savedEmployee = empService.saveEmployee(employeeDto);
        EmployeeDto savedEmployeeDto = ConvertEmployeeDto.convertEntityToDto(savedEmployee);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateEmployee")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employee) throws Exception {
        return ConvertEmployeeDto.convertEntityToDto(empService.saveEmployee(employee));
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

        return ResponseEntity.ok(ConvertEmployeeDto.convertListEntityToListDto(empService.getAllEmployees()));
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public void deleteEmployeeById(@PathVariable Integer id) {
        empService.deleteEmployeeById(id);
    }

    @GetMapping("/getEmployeeById")
    public EmployeeDto findEmployeesBySkillId(@RequestParam Integer employeeId) {
        return empService.getEmployeeById(employeeId);
    }

    @GetMapping("/getSalaryById/{employeeId}")
    public Map<String, Object> getSalaryById(@PathVariable Integer employeeId) {
        return empService.getSalariesById(employeeId);
    }

    @PutMapping("/updatePerformance/{employeeId}")
    public ResponseEntity<Employee> updateEmployeePerformance(@PathVariable Integer employeeId,@RequestBody EvaluationRatedDto evaluationRatedDto) {
        Employee updatedEmployee = empService.updateEmployeePerformanceAndSalary(employeeId,evaluationRatedDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }


}







