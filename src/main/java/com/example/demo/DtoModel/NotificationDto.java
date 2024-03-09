package com.example.demo.DtoModel;


import com.example.demo.entity.Employee;
import com.example.demo.entity.Vacation;
import com.example.demo.entity.VacationStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private Integer notificationId;
    private String notificationMessage;
    private Date notificationCreationDate;
    private boolean notificationRead;
    private VacationStatus status;
    private Vacation vacation;
    private Employee employee;
}
