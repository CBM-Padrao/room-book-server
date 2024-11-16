package com.project.roombook.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class BookingCreateDTO {
    @NotNull(message = "O ID da sala não pode ser nula")
    private Long roomId;
    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Long userId;
    @NotNull(message = "A dataInicio da reserva não pode ser nulo")
    private LocalDateTime startTime;
    @NotNull(message = "A dataFinal da reserva pode ser nulo")
    private LocalDateTime endTime;
    private List<Long> participantIds;

    public BookingCreateDTO() {
    }

    public BookingCreateDTO(Long roomId, Long userId, LocalDateTime startTime, LocalDateTime endTime, List<Long> participantIds) {
        this.roomId = roomId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participantIds = participantIds;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Long> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(List<Long> participantIds) {
        this.participantIds = participantIds;
    }
}
