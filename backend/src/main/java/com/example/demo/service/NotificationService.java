package com.example.demo.service;

import com.example.demo.entity.Notification;
import com.example.demo.entity.Vacation;
import com.example.demo.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Optional<Notification> findById(int id) {
        return notificationRepository.findById(id);
    }

    public void deleteById(int id) {
        notificationRepository.deleteById(id);
    }

    public Page<Notification> findAllByVacationIn(List<Vacation> vacations, PageRequest of) {
        return  notificationRepository.findAllByVacationIn(vacations, of);
    }

    public Long countByVacationIn(List<Vacation> vacations) {
        return notificationRepository.countByVacationIn(vacations);
    }
}
