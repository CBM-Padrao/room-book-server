package com.project.roombook.repository;

import com.project.roombook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRegistration(String registration);
    Optional<UserDetails> findByRegistration(String registration);

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    List<User> findByIsDeletedFalseAndIdNot(Long id);

    Optional<User> findByIdAndIsDeletedFalse(Long id);
}
