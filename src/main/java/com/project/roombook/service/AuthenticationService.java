package com.project.roombook.service;

import com.project.roombook.dto.AuthenticationDTO;
import com.project.roombook.dto.LoginResponseDTO;
import com.project.roombook.dto.UserResponseDTO;
import com.project.roombook.entity.User;
import com.project.roombook.mapper.UserMapper;
import com.project.roombook.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.roombook.repository.UserRepository;
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
            throw new RuntimeException("Senha inválida!"); //TODO alterar
        }

        var token = tokenService.generateToken(authenticationDTO.registration());

        if (token == null) {
            throw new RuntimeException("Falha na autenticação!"); //TODO alterar
        }

        return new LoginResponseDTO(token, UserMapper.toResponseDTO((User) user));
    }
}