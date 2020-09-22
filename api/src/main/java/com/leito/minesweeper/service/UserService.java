package com.leito.minesweeper.service;

import com.leito.minesweeper.model.User;
import com.leito.minesweeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User login(String username) {
        Optional<User> optionalUser = userRepository.getUserByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);

        return user;
    }
}
