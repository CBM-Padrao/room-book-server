package com.project.roombook.dto;


import java.time.LocalDateTime;

public record BookingFilterDTO(Long roomId, Long userId,
                               LocalDateTime startTime, LocalDateTime endTime) {
}
