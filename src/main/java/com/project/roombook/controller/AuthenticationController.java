package com.project.roombook.controller;


import com.project.roombook.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.project.roombook.dto.AuthenticationDTO;
import com.project.roombook.dto.LoginResponseDTO;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        LoginResponseDTO loginResponseDTO = authenticationService.authenticate(authenticationDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

}
