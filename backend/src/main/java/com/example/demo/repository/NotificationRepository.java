package com.example.demo.repository;


import java.util.List;

import com.example.demo.entity.Notification;
import com.example.demo.entity.Vacation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>, JpaSpecificationExecutor<Notification> {

    long countByVacationIn(List<Vacation> vacations);

    Page<Notification> findAllByVacationIn(List<Vacation> vacations, Pageable pageable);

}