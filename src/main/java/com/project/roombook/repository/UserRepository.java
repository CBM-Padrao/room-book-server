package com.project.roombook.repository;

import com.project.roombook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRegistration(String registration);

    static UserDetails findByRegistration(String registration) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByRegistration'");
    }
}
