package com.example.demo.service;

import com.example.demo.DtoModel.ConvertNotificationDto;
import com.example.demo.DtoModel.NotificationDto;
import com.example.demo.DtoModel.VacationDto;
import com.example.demo.entity.*;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.VacationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private VacationRepository vacationRepository;


  @Transactional
    public void notifyAdminAboutVacationRequests(Vacation vacation) {
        // Fetch the admin from the database

        Employee admin = employeeRepository.findByRole(Role.ADMIN);

        // Create a notification
        Notification notification = new Notification();
        notification.setEmployee(admin);
        notification.setVacation(vacation);
        notification.setNotificationMessage("New vacation request from employee #" + vacation.getEmployee().getEmployeeId());
        notification.setNotificationCreationDate(new Date());
        notification.setNotificationRead(false);
        notification.setStatus(VacationStatus.PENDING); // Default status

        // Save the notification
        notificationRepository.save(notification);
    }



    @Transactional
    public void updateNotificationForVacationResponse(Vacation vacation, VacationStatus status, String reason) {
        // Update existing notifications related to the vacation
        for (Notification notification : vacation.getNotifications()) {
            if (notification.getStatus() == VacationStatus.PENDING) {
                // Update status based on admin response
                notification.setStatus(status);

                // Add reason if rejected
                if (status == VacationStatus.REJECTED) {
                    notification.setNotificationMessage("Vacation request rejected. Reason: " + reason);
                }
            }
        }
    }



    public List<Notification> getAllNotificationsByEmployee(Integer employeeId) {
        return notificationRepository.findAllByEmployeeEmployeeId(employeeId);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }


    @Transactional
    public List<NotificationDto> getAllNotifications_() {

        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(ConvertNotificationDto::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void notifyAdminAboutRequest(VacationDto vacationDto) {
        // Fetch the admin from the database
        Employee adminEmployee = employeeRepository.findByRole(Role.ADMIN);

        // Create a new Vacation entity
        Vacation vacation = new Vacation();
        // Set other properties from the DTO
        vacation.setVacationRequestDate(new Date());
        vacation.setVacationStartDate(vacationDto.getVacationStartDate());
        vacation.setVacationEndDate(vacationDto.getVacationEndDate());
        vacation.setVacationComment(vacationDto.getVacationComment());
        vacation.setStatus(VacationStatus.PENDING);
        vacation.setEmployee(adminEmployee);
        vacation.setVacationDuration(vacation.calculateVacationDuration());

        // Save the vacation entity
        vacationRepository.save(vacation);

        // Create a new Notification entity
        Notification notification = new Notification();
        notification.setEmployee(adminEmployee);
        notification.setVacation(vacation);
        notification.setNotificationMessage("New vacation request from employee #" + adminEmployee.getEmployeeId());
        notification.setNotificationCreationDate(new Date());
        notification.setNotificationRead(false);
        notification.setStatus(VacationStatus.PENDING); // Default status

        // Save the notification
        notificationRepository.save(notification);

        // Convert the saved entity back to DTO for response
        //return ConvertNotificationDto.convertEntityToDto(notification);
    }


}

