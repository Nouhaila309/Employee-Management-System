package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.DtoModel.RoleDTO;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    private final RoleService roleService;

    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("")
    public ResponseEntity<Page<Role>> getRolesPaginated(@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize, @RequestParam(name = "pageIndex", defaultValue = "0", required = false) Integer pageIndex) {
        return new ResponseEntity<>(roleService.findAll(PageRequest.of(pageIndex, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Role>> getRoleById(@PathVariable("id") int id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveRole(@RequestBody RoleDTO role) {
        return new ResponseEntity<>(roleService.saveAndFlush(mapper.map(role, Role.class)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoleById(@PathVariable("id") int id) {
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}