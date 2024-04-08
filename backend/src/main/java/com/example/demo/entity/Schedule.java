package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedule")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    //Schedule ID, Work hours, Days of the week.


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer scheduleId;

   /* @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;*/




}