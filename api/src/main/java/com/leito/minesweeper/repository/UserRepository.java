package com.leito.minesweeper.repository;

import com.leito.minesweeper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
}
