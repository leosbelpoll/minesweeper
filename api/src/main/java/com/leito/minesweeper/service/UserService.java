package com.leito.minesweeper.service;

import com.leito.minesweeper.dto.UserSummaryResponse;
import com.leito.minesweeper.model.User;
import com.leito.minesweeper.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GameService gameService;

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

    public User get(Long id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NotFoundException("User not found");
        }

        return optionalUser.get();
    }

    public UserSummaryResponse getSummary(Long id) {
        UserSummaryResponse summaryResponse = new UserSummaryResponse();
        summaryResponse.setUnfinishedGames(gameService.getUnfinishedGamesByUserId(id));
        summaryResponse.setFinishedGames(gameService.getFinishedGamesByUserId(id));

        return summaryResponse;
    }
}
