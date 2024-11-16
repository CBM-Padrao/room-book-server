package com.project.roombook.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class BookingUpdateDTO {
    @NotNull(message = "O id do usuário não pode ser nullo")
    private Long id;

    private Long roomId;

    private Date startTime;

    private Date endTime;

    public BookingUpdateDTO() {
    }

    public BookingUpdateDTO(Long id, Long roomId, Date startTime, Date endTime) {
        this.id = id;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
