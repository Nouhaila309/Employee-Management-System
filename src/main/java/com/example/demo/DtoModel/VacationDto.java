package com.example.demo.DtoModel;
import com.example.demo.entity.Employee;
import com.example.demo.entity.VacationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacationDto {

    private Integer vacationId;
    private Date vacationRequestDate;
    private Date vacationStartDate;
    private Date vacationEndDate;
    private float vacationDuration;
    private String vacationComment;
 //   private  EmployeeDto employeeDto;
    private VacationStatus status;

}
