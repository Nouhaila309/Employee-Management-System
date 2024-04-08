package com.example.demo.repository;


import com.example.demo.entity.Notification;
import com.example.demo.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    long countByVacationIn(List<Vacation> vacations);

    List<Notification> findAllByEmployeeEmployeeId(Integer employeeId);
}
