package com.example.demo.controller;


import com.example.demo.DtoModel.ConvertNotificationDto;

import com.example.demo.DtoModel.NotificationDto;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Notification;
import com.example.demo.entity.Vacation;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.VacationRepository;
import com.example.demo.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/notifications")
@AllArgsConstructor
@NoArgsConstructor
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/getAllNotifications")
    public ResponseEntity<List<NotificationDto>> getAllEmployees() {
        return ResponseEntity.ok(ConvertNotificationDto.convertListEntityToListDto(notificationService.getAllNotifications()));
    }

    @Transactional
    @GetMapping("/count/{employeeId}")
    public ResponseEntity<Long> getCountNotificationById(@PathVariable Integer employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        List<Vacation> vacations = vacationRepository.findAllByEmployee(employee);
        return new ResponseEntity<>(notificationRepository.countByVacationIn(vacations), HttpStatus.OK);
    }

    @Transactional

    @GetMapping("/getAllNotificationsByEmployee/{employeeId}")
    public ResponseEntity<List<NotificationDto>> getAllNotificationsByEmployee(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(ConvertNotificationDto.convertListEntityToListDto(notificationService.getAllNotificationsByEmployee(employeeId)));
    }

}