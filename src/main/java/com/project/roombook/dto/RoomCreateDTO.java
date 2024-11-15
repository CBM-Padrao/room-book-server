package com.project.roombook.dto;


public class RoomCreateDTO {
    private String name;

    public RoomCreateDTO() {
    }

    public RoomCreateDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
