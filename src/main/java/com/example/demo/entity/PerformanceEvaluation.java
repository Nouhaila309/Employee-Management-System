package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerformanceEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evaluationId;
    private String evaluationRating;
    private String evaluationComment;

    @Enumerated(EnumType.STRING)
    private EvaluationRated evaluationRated;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


 /*   @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;*/
}
