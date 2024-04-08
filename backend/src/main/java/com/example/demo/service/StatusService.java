package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public Page<Status> findAll(PageRequest pageRequest) {
        return statusRepository.findAll(pageRequest);
    }

    public Optional<Status> findById(int id) {
        return statusRepository.findById(id);
    }

    public Status saveAndFlush(Status status) {
        return statusRepository.saveAndFlush(status);
    }

    public void deleteById(int id) {
        statusRepository.deleteById(id);
    }
}
