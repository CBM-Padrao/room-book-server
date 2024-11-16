package com.project.roombook.dto;


import java.util.Date;

public record BookingFilterDTO(Long roomId, Long userId,
                               Date startTime, Date endTime) {
}
