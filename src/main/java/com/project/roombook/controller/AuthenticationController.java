package com.project.roombook.controller;


//import javax.smartcardio.ResponseAPDU;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.roombook.dto.AuthenticationDTO;
import com.project.roombook.dto.LoginResponseDTO;
import com.project.roombook.entity.User;
import com.project.roombook.service.TokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationDTO data) {
        try {
            
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.Registration(), data.Password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
        
    } catch (Exception e) {
        // Trata falhas de autenticação, retornando uma mensagem de erro
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: " + e.getMessage());
    }
}

}

