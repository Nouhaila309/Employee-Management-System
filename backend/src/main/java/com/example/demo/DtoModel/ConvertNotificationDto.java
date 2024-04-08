package com.example.demo.DtoModel;

/*import com.example.demo.entity.Notification;
import com.example.demo.entity.Vacation;*/

import com.example.demo.entity.Employee;
import com.example.demo.entity.Notification;

import java.util.ArrayList;
import java.util.List;

public class ConvertNotificationDto {


    public static NotificationDto convertEntityToDto(Notification notificationEntity) {
        return NotificationDto.builder()
                .notificationId(notificationEntity.getNotificationId())
                .notificationMessage(notificationEntity.getNotificationMessage())
                .notificationCreationDate(notificationEntity.getNotificationCreationDate())
                .notificationRead(notificationEntity.isNotificationRead())
                //  .vacation(notificationEntity.getVacation())
                .status(notificationEntity.getStatus())
                .build();


    }

    public static Notification convertDtoToEntity(NotificationDto notificationDto) {
        return Notification.builder()
                .notificationId(notificationDto.getNotificationId())
                .notificationMessage(notificationDto.getNotificationMessage())
                .notificationCreationDate(notificationDto.getNotificationCreationDate())
                .notificationRead(notificationDto.isNotificationRead())
                .vacation(notificationDto.getVacation())
                .status(notificationDto.getStatus())
                .build();
    }

    public static List<NotificationDto> convertListEntityToListDto(List<Notification> notificationList) {
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for (Notification notif : notificationList) {
            notificationDtoList.add(convertEntityToDto(notif));
        }
        return notificationDtoList;
    }


}