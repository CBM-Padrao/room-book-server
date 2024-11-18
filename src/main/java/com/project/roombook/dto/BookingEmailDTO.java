package com.project.roombook.dto;

import java.time.LocalDateTime;

public class BookingEmailDTO {
    private String title;
    private String roomName;
    private LocalDateTime startTime;
    private String userName;
    private String userEmail;

    public BookingEmailDTO(String title, String roomName, LocalDateTime startTime, String userName, String userEmail) {
        this.title = title;
        this.roomName = roomName;
        this.startTime = startTime;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

