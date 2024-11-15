package com.project.roombook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.roombook.repository.UserRepository;

public class AuthorizationService implements UserDetailsService{

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String registration) throws UsernameNotFoundException {
        
        return UserRepository.findByRegistration(registration);
    }
    
}