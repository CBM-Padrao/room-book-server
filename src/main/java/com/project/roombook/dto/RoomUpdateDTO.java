package com.project.roombook.dto;

import java.util.Date;

public class RoomUpdateDTO {
    private Long id;
    private String name;

    public RoomUpdateDTO() {
    }

    public RoomUpdateDTO(Long id, String name, Date disabledUntil) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
