package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Notification;
import com.example.demo.entity.Vacation;
import com.example.demo.service.NotificationService;
import com.example.demo.service.VacationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/notifications")
public class NotificationRestController {

    private final NotificationService notificationService;

    private final VacationService vacationService;

    @GetMapping("/user/{username}")
    public ResponseEntity<Page<Notification>> getNotificationsPaginated(
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "pageIndex", defaultValue = "0", required = false) Integer pageIndex,
            @PathVariable("username") String username) {
        List<Vacation> vacations = vacationService.findAllByEmployee(new Employee(username));
        return new ResponseEntity<>(
                notificationService.findAllByVacationIn(vacations, PageRequest.of(pageIndex, pageSize)),
                HttpStatus.OK);
    }

    @GetMapping("/count/{username}")
    public ResponseEntity<Long> getCountNotificationById(@PathVariable("username") String username) {
        List<Vacation> vacations = vacationService.findAllByEmployee(new Employee(username));
        return new ResponseEntity<>(notificationService.countByVacationIn(vacations), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Notification>> getNotificationById(@PathVariable("id") int id) {
        return new ResponseEntity<>(notificationService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNotificationById(@PathVariable("id") int id) {
        notificationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}