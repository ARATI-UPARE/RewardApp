package com.example.reward.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reward.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}