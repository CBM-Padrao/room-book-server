package com.project.roombook.service;

import com.project.roombook.dto.UserCreateDTO;
import com.project.roombook.dto.UserResponseDTO;
import com.project.roombook.entity.User;
import com.project.roombook.exceptions.UserAlreadyExistsException;
import com.project.roombook.mapper.UserMapper;
import com.project.roombook.repository.UserRepository;
import com.project.roombook.util.PasswordUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        if (userRepository.existsByRegistration(userCreateDTO.getRegistration())) {
            throw new UserAlreadyExistsException("A matrícula já está em uso. Por favor, escolha outra.");
        }

        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new UserAlreadyExistsException("O e-mail já está em uso. Por favor, informe outro.");
        }

        User user = UserMapper.toEntity(userCreateDTO);
        String encodedPassword = PasswordUtils.hashPassword(userCreateDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedAt(new Date());
        userRepository.save(user);
        return UserMapper.toResponseDTO(user);
    }
}
