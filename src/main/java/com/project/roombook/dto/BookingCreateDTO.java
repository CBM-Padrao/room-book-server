package com.project.roombook.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class BookingCreateDTO {
    @NotNull(message = "O sala da reserva não pode ser nula")
    private RoomResponseDTO room;
    @NotNull(message = "O usuário da reserva não pode ser nulo")
    private UserResponseDTO user;
    @NotNull(message = "A dataInicio da reserva não pode ser nulo")
    private Date startTime;
    @NotNull(message = "A dataFinal da reserva pode ser nulo")
    private Date endTime;

    public BookingCreateDTO() {
    }

    public BookingCreateDTO(RoomResponseDTO room, UserResponseDTO user, Date startTime, Date endTime) {
        this.room = room;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RoomResponseDTO getRoom() {
        return room;
    }

    public void setRoom(RoomResponseDTO room) {
        this.room = room;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
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
