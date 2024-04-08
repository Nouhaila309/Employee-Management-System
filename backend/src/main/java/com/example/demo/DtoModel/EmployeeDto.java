package com.example.demo.DtoModel;

import java.util.Date;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private String username;
    private Employee responsible;
    private Role role;
    private String password;
    private String fullName;
    private String email;
    private Date joinDate;
    private Date leaveDate;
    private float initialBalance;
    private float currentBalance;

}