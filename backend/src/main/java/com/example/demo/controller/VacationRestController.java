package com.example.demo.controller;

import com.example.demo.DtoModel.VacationDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Vacation;
import com.example.demo.entity.VacationStatus;
import com.example.demo.service.VacationService;
import com.example.demo.service.VacationStatusService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/vacations")
public class VacationRestController {

    private final VacationService vacationService;
    private final VacationStatusService vacationStatusService;
    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("")
    public ResponseEntity<Page<Vacation>> vacations(
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "pageIndex", defaultValue = "0", required = false) Integer pageIndex) {
        return new ResponseEntity<>(vacationService.findAll(PageRequest.of(pageIndex, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vacation>> vacationById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vacationService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/approve/{id}")
    public void approveVacation(@PathVariable("id") int id) {
        Optional<Vacation> op = vacationService.findById(id);
        VacationStatus status = vacationStatusService.findById(2).get();
        if (op.isPresent()) {
            Vacation v = op.get();
            v.setVacationStatus(status);
            vacationService.saveAndFlush(v);
        }
    }

    @GetMapping("/reject/{id}")
    public void rejectVacation(@PathVariable("id") int id) {
        Optional<Vacation> op = vacationService.findById(id);
        VacationStatus status = vacationStatusService.findById(3).get();
        if (op.isPresent()) {
            Vacation v = op.get();
            v.setVacationStatus(status);
            vacationService.saveAndFlush(v);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveVacation(@RequestBody VacationDTO vacation) {
        return new ResponseEntity<>(vacationService.saveAndFlush(mapper.map(vacation, Vacation.class)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVacationById(@PathVariable("id") int id) {
        vacationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<Vacation>> allVacationsByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(vacationService.findAllByEmployee(new Employee(username)), HttpStatus.OK);

    }
}