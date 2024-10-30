package com.project.roombook.dto;

import java.util.Date;

public class UserCreateDTO {
    private String registration;
    private String name;
    private String role;
    private String email;
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String registration, String name, String role, String email, String password) {
        this.registration = registration;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
