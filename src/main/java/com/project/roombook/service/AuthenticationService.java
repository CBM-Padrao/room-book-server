package com.project.roombook.service;

import com.project.roombook.dto.AuthenticationDTO;
import com.project.roombook.dto.LoginResponseDTO;
import com.project.roombook.entity.User;
import com.project.roombook.exceptions.AuthenticationFailureException;
import com.project.roombook.exceptions.InvalidPasswordException;
import com.project.roombook.mapper.UserMapper;
import com.project.roombook.repository.UserRepository;
import com.project.roombook.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    public LoginResponseDTO authenticate(AuthenticationDTO authenticationDTO) {
        UserDetails user = userRepository.findByRegistration(authenticationDTO.registration())
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        if (!PasswordUtils.checkPassword(authenticationDTO.password(), user.getPassword())) {
            throw new InvalidPasswordException("Senha inválida!");
        }

        var token = tokenService.generateToken(authenticationDTO.registration());

        if (token == null) {
            throw new AuthenticationFailureException("Falha na autenticação!");
        }

        return new LoginResponseDTO(token, UserMapper.toResponseDTO((User) user));
    }
}