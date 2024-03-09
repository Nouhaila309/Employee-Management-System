package com.example.demo.DtoModel;

import com.example.demo.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConvertEmployeeDto {


    public static EmployeeDto convertEntityToDto(Employee employeeEntity) {
        return EmployeeDto.builder().employeeId(employeeEntity.getEmployeeId())
                .employeeFirstName(employeeEntity.getEmployeeFirstName())
                .employeeLastName(employeeEntity.getEmployeeLastName())
                .employeeCity(employeeEntity.getEmployeeCity())
                .employeeDiploma(employeeEntity.getEmployeeDiploma())
                .joinCompanyStartDate(employeeEntity.getJoinCompanyStartDate())
                .employeeEmail(employeeEntity.getEmployeeEmail())
                .employeeGender(employeeEntity.getEmployeeGender())
                .employeePhoneNumber(employeeEntity.getEmployeePhoneNumber())
                .employeeSalary(employeeEntity.getEmployeeSalary())
                .role(employeeEntity.getRole())
                .evaluationRated((employeeEntity.getEvaluationRated()))
                .build();
    }

    public static List<EmployeeDto> convertListEntityToListDto(List<Employee> employeeList) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee emp : employeeList) {
            employeeDtoList.add(convertEntityToDto(emp));
        }
        return employeeDtoList;
    }

    public static Employee convertDtoToEntity(EmployeeDto employeeDto) {

        return Employee.builder().employeeId(employeeDto.getEmployeeId())
                .employeeFirstName(employeeDto.getEmployeeFirstName())
                .employeeLastName(employeeDto.getEmployeeLastName())
                .employeeCity(employeeDto.getEmployeeCity())
                .employeeDiploma(employeeDto.getEmployeeDiploma())
                .joinCompanyStartDate(employeeDto.getJoinCompanyStartDate())
                .employeeEmail(employeeDto.getEmployeeEmail())
                .employeeGender(employeeDto.getEmployeeGender())
                .employeePhoneNumber(employeeDto.getEmployeePhoneNumber())
                .employeeSalary(employeeDto.getEmployeeSalary())
                .role(employeeDto.getRole())
                .evaluationRated(employeeDto.getEvaluationRated())
                .build();
    }

    public static List<Employee> convertListDtoToListEntity(List<EmployeeDto> employeeDtoList) {
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeDto emp : employeeDtoList) {
            employeeList.add(convertDtoToEntity(emp));
        }
        return employeeList;
    }


    public static List<EmployeeDto> convertOptionalEntityToListDto(Optional<Employee> optionalEmployee) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        optionalEmployee.ifPresent(emp -> employeeDtoList.add(convertEntityToDto(emp)));
        return employeeDtoList;
    }


}
