package com.example.demo.service;

import com.example.demo.DtoModel.ConvertVacationDto;
import com.example.demo.DtoModel.VacationDto;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Vacation;
import com.example.demo.entity.VacationStatus;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.VacationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class VacationService {
    @Autowired
    public VacationRepository vacationRepository;
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public  NotificationService notificationService;

    public List<VacationDto> getAllVacations() {
        VacationDto vacationDto = new VacationDto();

        return ConvertVacationDto.convertListEntityToListDto(vacationRepository.findAll());
    }

    public List<VacationDto> getAllVacationsByEmployee(Integer employeeId) {
        Optional<Employee> employee=this.employeeRepository.findById(employeeId);
        return employee.map(value -> ConvertVacationDto.convertListEntityToListDto(vacationRepository.findAllByEmployee(value))).orElse(null);
    }


    @Transactional
    public VacationDto createVacationToEmployee(VacationDto vacationDto, Integer employeeId) {
        // Fetch the employee from the database
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        // Create a new Vacation entity
        Vacation vacation = new Vacation();

        // Set other properties from the DTO
        vacation.setVacationRequestDate(new Date());
        vacation.setVacationStartDate(vacationDto.getVacationStartDate());
        vacation.setVacationEndDate(vacationDto.getVacationEndDate());
        vacation.setVacationComment(vacationDto.getVacationComment());
        vacation.setStatus(VacationStatus.PENDING);
        vacation.setEmployee(employee);
        vacation.setVacationDuration(vacation.calculateVacationDuration());

        // Save the vacation entity
        vacationRepository.save(vacation);


        // Notify the admin
        notificationService.notifyAdminAboutRequest(vacationDto);


        // Convert the saved entity back to DTO for response
        return ConvertVacationDto.convertEntityToDto(vacation);


    }
}
