package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Page<Role> findAll(PageRequest pageRequest) {
        return roleRepository.findAll(pageRequest);
    }

    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
    }

    public Role saveAndFlush(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    public void deleteById(int id) {
        roleRepository.deleteById(id);
    }
}
