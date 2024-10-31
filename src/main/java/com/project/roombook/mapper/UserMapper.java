package com.project.roombook.mapper;

import com.project.roombook.dto.UserCreateDTO;
import com.project.roombook.dto.UserResponseDTO;
import com.project.roombook.entity.User;

public class UserMapper {
    public static User toEntity(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setRegistration(userCreateDTO.getRegistration());
        user.setName(userCreateDTO.getName());
        user.setRole(userCreateDTO.getRole());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        return user;
    }

    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getRegistration(),
                user.getName(),
                user.getRole(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
