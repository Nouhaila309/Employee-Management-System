package com.example.demo.controller;
import com.example.demo.DtoModel.ConvertVacationDto;
import com.example.demo.DtoModel.VacationDto;
import com.example.demo.entity.Vacation;
import com.example.demo.repository.VacationRepository;
import com.example.demo.service.VacationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/vacations")
@AllArgsConstructor

public class VacationController {
    @Autowired
    public VacationService vacationService;


    @GetMapping("/getAllVacations")
    public List<VacationDto> gatAllVacations() {
        return vacationService.getAllVacations();
    }
    @GetMapping("/getAllVacations_")
    public ResponseEntity<List<VacationDto>> getAllVacationsTest() {

        return ResponseEntity.ok(vacationService.getAllVacations());
    }

    @PostMapping("/addVacation/{employeeId}")
    public ResponseEntity<VacationDto> createVacation(@PathVariable Integer employeeId, @RequestBody VacationDto vacationDto) {
        VacationDto createdVacationDto = vacationService.createVacationToEmployee(vacationDto, employeeId);
        return new ResponseEntity<>(createdVacationDto, HttpStatus.CREATED);
    }
    @GetMapping("/getVacationByEmployee/{employeeId}")
    public List<VacationDto> getVacationByEmployee(@PathVariable Integer employeeId) {
        return vacationService.getAllVacationsByEmployee(employeeId);
    }


}
