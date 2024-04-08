package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "project-id", unique = true, nullable = false)
    private int projectId;

    @Column(name = "project-name", nullable = false)
    private String projectName;
    @Column(name = "project-shortName")
    private String projectShortName;
    @Temporal(TemporalType.DATE)
    @Column(name = "project-startDate", nullable = false, length = 10)
    private Date projectStartDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "project-endDate", length = 10)
    private Date projectEndDate;

 /*  @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Project_Employee", joinColumns = {
            @JoinColumn(name = "projectId", nullable = false, updatable = false, insertable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "employeeId", nullable = false, updatable = false, insertable = false)})
    private Set<Employee> employees = new HashSet<>(0);*/

}
