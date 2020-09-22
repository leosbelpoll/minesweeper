package com.leito.minesweeper.service;

import com.leito.minesweeper.model.Game;
import com.leito.minesweeper.repository.GameRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.Date;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public Game get(Long id) throws NotFoundException {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (!optionalGame.isPresent()) {
            throw new NotFoundException("Game not found");
        }
        return optionalGame.get();
    }

    public Game start(Integer rows, Integer columns, Integer mines) {
        Game game = new Game(rows, columns, mines);
        gameRepository.save(game);

        return game;
    }

    public void save(Long id) throws NotFoundException, OperationNotSupportedException {
        Game game = this.get(id);
        if (game.isSaved()) {
            throw new OperationNotSupportedException("Game is already saved");
        }
        Date now = new Date();
        long diff = now.getTime() - game.getLastResume().getTime();
        game.setPlayedTime(diff);
        game.setLastResume(null);
        gameRepository.save(game);
    }
}
