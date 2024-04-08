package com.example.demo.repository;

import com.example.demo.entity.VacationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationStatusRepository extends JpaRepository<VacationStatus, Integer>, JpaSpecificationExecutor<VacationStatus> {

}
