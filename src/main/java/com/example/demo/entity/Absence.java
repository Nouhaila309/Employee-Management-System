package com.example.demo.entity;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "absence")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absenceId", unique = true, nullable = false)
    private Integer absenceId;
    private Date absenceDate;
    private String absenceReason;


    /*@ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;*/
}
