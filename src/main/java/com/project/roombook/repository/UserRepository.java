package com.project.roombook.repository;

import com.project.roombook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRegistration(String registration);
    boolean existsByEmail(String email);
    List<User> findByIsDeletedFalse();

    Optional<User> findByIdAndIsDeletedFalse(Long id);
}
