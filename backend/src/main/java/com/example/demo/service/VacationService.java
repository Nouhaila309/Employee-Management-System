package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Vacation;
import com.example.demo.repository.VacationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VacationService {

    private final VacationRepository vacationRepository;

    public Page<Vacation> findAll(PageRequest pageRequest) {
        return vacationRepository.findAll(pageRequest);
    }

    public Optional<Vacation> findById(int id) {
        return vacationRepository.findById(id);
    }

    public Vacation saveAndFlush(Vacation vacation) {
        return vacationRepository.saveAndFlush(vacation);
    }

    public void deleteById(int id) {
        vacationRepository.deleteById(id);
    }

    public List<Vacation> findAllByEmployee(Employee employee) {
        return vacationRepository.findAllByEmployee(employee);
    }
}
