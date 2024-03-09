package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vacationId;
    private Date vacationRequestDate;
    private Date vacationStartDate;
    private Date vacationEndDate;
    private float vacationDuration;
    private String vacationComment;

    @Enumerated(EnumType.STRING)
    private VacationStatus status = VacationStatus.PENDING;// Set default status to PENDING

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Notification> notifications;

    public float calculateVacationDuration() {
        long diffInMillies = Math.abs(vacationEndDate.getTime() - vacationStartDate.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diffInDays;
    }






    /*@ManyToOne
    private Employee employee;*/
}
