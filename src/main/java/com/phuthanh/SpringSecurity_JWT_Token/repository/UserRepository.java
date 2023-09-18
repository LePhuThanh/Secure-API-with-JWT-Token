package com.phuthanh.SpringSecurity_JWT_Token.repository;

import com.phuthanh.SpringSecurity_JWT_Token.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
