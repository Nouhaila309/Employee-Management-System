package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.DtoModel.EmployeeDTO;
import com.example.demo.DtoModel.LoginDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
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
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("")
    public ResponseEntity<Page<Employee>> getEmployeePaginated(@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize, @RequestParam(name = "pageIndex", defaultValue = "0", required = false) Integer pageIndex) {
        return new ResponseEntity<>(employeeService.findAll(PageRequest.of(pageIndex, pageSize)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public Employee employeeLogin(@RequestBody LoginDTO loginDTO) {
        Optional<Employee> employeeObj = employeeService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        return employeeObj.orElse(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> employeeById(@PathVariable("id") String id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeDTO employee) {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String password = RandomStringUtils.random(length, useLetters, useNumbers);
        employee.setPassword(password);
        return new ResponseEntity<>(employeeService.saveAndFlush(mapper.map(employee, Employee.class)), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") String id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}