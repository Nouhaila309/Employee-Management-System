package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "employee")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
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
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private EvaluationRated evaluationRated;


    /* @OneToMany(mappedBy = "employee")
    private Set<Vacation> vacations = new HashSet<>();
@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private Set<Notification> notifications;*/
    /*@ManyToOne
    @JoinColumn(name = "positionId")
    private Position position;*/

   /* @ManyToMany(mappedBy = "employee")
    private List<Project> projects = new ArrayList<>();

   /* @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<PerformanceEvaluation> performanceEvaluations = new HashSet<>();*/

   /* @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Schedule> schedule = new HashSet<>();*/



   /* @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Absence> absences = new HashSet<>();*/
}
