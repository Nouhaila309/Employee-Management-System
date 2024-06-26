package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.DtoModel.StatusDTO;
import com.example.demo.entity.Status;
import com.example.demo.service.StatusService;
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
@RequestMapping("/api/status")
public class StatusRestController {

    private final StatusService statusService;

    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("")
    public ResponseEntity<Page<Status>> getStatusPaginated(
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "pageIndex", defaultValue = "0", required = false) Integer pageIndex) {
        return new ResponseEntity<>(statusService.findAll(PageRequest.of(pageIndex, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Status>> getStatusById(@PathVariable("id") int id) {
        return new ResponseEntity<>(statusService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveStatus(@RequestBody StatusDTO status) {
        return new ResponseEntity<>(statusService.saveAndFlush(mapper.map(status, Status.class)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStatusById(@PathVariable("id") int id) {
        statusService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}