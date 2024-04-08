package com.example.demo.service;

import com.example.demo.entity.VacationStatus;
import com.example.demo.repository.VacationStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VacationStatusService {

    private final VacationStatusRepository vacationStatusRepository;

    public Page<VacationStatus> findAll(PageRequest pageRequest) {
        return vacationStatusRepository.findAll(pageRequest);
    }

    public Optional<VacationStatus> findById(int id) {
        return vacationStatusRepository.findById(id);
    }

    public VacationStatus saveAndFlush(VacationStatus status) {
        return vacationStatusRepository.saveAndFlush(status);
    }

    public void deleteById(int id) {
        vacationStatusRepository.deleteById(id);
    }
}
