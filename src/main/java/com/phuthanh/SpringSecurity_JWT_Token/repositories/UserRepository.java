package com.phuthanh.SpringSecurity_JWT_Token.repositories;

import com.phuthanh.SpringSecurity_JWT_Token.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
