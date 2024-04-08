package com.example.demo.DtoModel;

import java.util.Date;

import com.example.demo.entity.Vacation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private int id;
    private Vacation vacation;
    private String message;
    private Date date;
    private boolean seen;

}