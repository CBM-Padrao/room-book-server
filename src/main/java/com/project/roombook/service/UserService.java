package com.project.roombook.service;

import com.project.roombook.dto.UserCreateDTO;
import com.project.roombook.dto.UserResponseDTO;
import com.project.roombook.dto.UserUpdateDTO;
import com.project.roombook.entity.User;
import com.project.roombook.exceptions.UserAlreadyExistsException;
import com.project.roombook.mapper.UserMapper;
import com.project.roombook.repository.UserRepository;
import com.project.roombook.util.PasswordUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
        userRepository.save(user);
        return UserMapper.toResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userUpdateDTO.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (userUpdateDTO.getName() != null) {
            user.setName(userUpdateDTO.getName());
        }

        if (userUpdateDTO.getRole() != null) {
            user.setRole(userUpdateDTO.getRole());
        }

        if (userUpdateDTO.getEmail() != null) {
            if (userRepository.existsByEmailAndIdNot(userUpdateDTO.getEmail(), userUpdateDTO.getId())) {
                throw new UserAlreadyExistsException("O e-mail já está em uso. Por favor, informe outro.");
            }
            user.setEmail(userUpdateDTO.getEmail());
        }

        if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()) {
            String encodedPassword = PasswordUtils.hashPassword(userUpdateDTO.getPassword());
            user.setPassword(encodedPassword);
        }

        userRepository.save(user);
        return UserMapper.toResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO deleteUser(Long id) {
        User user = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setDeleted(true);
        userRepository.save(user);
        return UserMapper.toResponseDTO(user);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrada"));
        return UserMapper.toResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers(Long id) {
        List<User> users = userRepository.findByIsDeletedFalseAndIdNot(id);
        return users.stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
