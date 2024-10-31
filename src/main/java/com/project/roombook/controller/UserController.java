package com.project.roombook.controller;

import com.project.roombook.dto.UserCreateDTO;
import com.project.roombook.dto.UserResponseDTO;
import com.project.roombook.exceptions.UserAlreadyExistsException;
import com.project.roombook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.createUser(userCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "error", "Usuário já existe",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Erro interno",
                    "message", "Erro ao salvar usuário: " + e.getMessage()
            ));
        }
    }
}
