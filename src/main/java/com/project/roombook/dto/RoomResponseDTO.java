package com.project.roombook.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class RoomResponseDTO {
    @NotNull(message = "O id da sala não pode ser nullo")
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public RoomResponseDTO() {
    }

    public RoomResponseDTO(Long id, String name, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
