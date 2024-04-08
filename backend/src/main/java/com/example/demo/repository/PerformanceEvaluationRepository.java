package com.example.demo.repository;

import com.example.demo.entity.PerformanceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation, Integer> {
}