package com.example.demo.DtoModel;

import com.example.demo.entity.Vacation;

import java.util.ArrayList;
import java.util.List;


public class ConvertVacationDto {
    public static VacationDto convertEntityToDto(Vacation vacationEntity) {
        return VacationDto.builder()
                .vacationId(vacationEntity.getVacationId())
                .vacationRequestDate(vacationEntity.getVacationRequestDate())
                .vacationStartDate(vacationEntity.getVacationStartDate())
                .vacationEndDate(vacationEntity.getVacationEndDate())
                .vacationDuration(vacationEntity.calculateVacationDuration())
                .vacationComment(vacationEntity.getVacationComment())
              //  .employeeId(vacationEntity.getEmployee().getEmployeeId()) // Set employeeId
                .status(vacationEntity.getStatus())
                .build();
    }

    public static Vacation convertDtoToEntity(VacationDto vacationDto) {
        return Vacation.builder()
                .vacationId(vacationDto.getVacationId())
                .vacationRequestDate(vacationDto.getVacationRequestDate())
                .vacationStartDate(vacationDto.getVacationStartDate())
                .vacationEndDate(vacationDto.getVacationEndDate())
                .vacationDuration(vacationDto.getVacationDuration())
                .vacationComment(vacationDto.getVacationComment())
                .build();
    }


    public static List<VacationDto> convertListEntityToListDto(List<Vacation> vacationList) {
        List<VacationDto> vacationDtoList = new ArrayList<>();
        for (Vacation vacation : vacationList) {
            vacationDtoList.add(convertEntityToDto(vacation));
        }
        return vacationDtoList;
    }


    public static List<Vacation> convertListDtoToEntity(List<VacationDto> vacationDtoList) {
        List<Vacation> vacationList = new ArrayList<>();
        for (VacationDto vacationDto : vacationDtoList) {
            vacationList.add(convertDtoToEntity(vacationDto));
        }
        return vacationList;
    }

}
