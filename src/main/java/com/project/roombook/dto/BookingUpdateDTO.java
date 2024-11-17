package com.project.roombook.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class BookingUpdateDTO {
    @NotNull(message = "O id do usuário não pode ser nullo")
    private Long id;
    private String title;

    private Long roomId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    private List<Long> participantIds;

    public BookingUpdateDTO() {
    }

    public BookingUpdateDTO(Long id, String title, Long roomId, LocalDateTime startTime, LocalDateTime endTime, List<Long> participantIds) {
        this.id = id;
        this.title = title;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participantIds = participantIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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
