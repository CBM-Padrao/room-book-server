package com.project.roombook.dto;

import jakarta.validation.constraints.NotNull;

public class UserUpdateDTO {
    @NotNull(message = "O id do usuário não pode ser nullo")
    private Long id;
    private String name;
    private String role;
    private String email;
    private String password;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(Long id, String name, String role, String email, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
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
