package com.project.roombook.dto;

import java.util.Date;

public class UserResponseDTO {
    private Long id;
    private String registration;
    private String name;
    private String role;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String registration, String name, String role, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.registration = registration;
        this.name = name;
        this.role = role;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
