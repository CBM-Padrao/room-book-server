package com.project.roombook.dto;

import java.util.Date;

public class BookingResponseDTO {
    private Long id;

    private RoomResponseDTO room;

    private UserResponseDTO user;

    private Date startTime;

    private Date endTime;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(RoomResponseDTO room, UserResponseDTO user, Date startTime, Date endTime) {
        this.room = room;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
