package com.example.demo.DtoModel;
import com.example.demo.entity.EvaluationRated;
import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDto {

    private Integer employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private Double employeeSalary;
    private String employeeGender;
    private String employeeEmail;
    private String employeePhoneNumber;
    private String employeeCity;
    private String employeeDiploma;
    private LocalDate joinCompanyStartDate;
    private Role role;
    private EvaluationRated evaluationRated;

}

