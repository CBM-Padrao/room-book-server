package com.project.roombook.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponseDTO {
    private Long id;

    private RoomResponseDTO room;

    private UserResponseDTO user;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    private List<UserResponseDTO> participants;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long id, RoomResponseDTO room, UserResponseDTO user, LocalDateTime startTime,
                              LocalDateTime endTime, List<UserResponseDTO> participants) {
        this.id = id;
        this.room = room;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
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

    public List<UserResponseDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserResponseDTO> participants) {
        this.participants = participants;
    }
}
