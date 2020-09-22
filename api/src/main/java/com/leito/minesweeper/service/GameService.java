package com.leito.minesweeper.service;

import com.leito.minesweeper.model.Game;
import com.leito.minesweeper.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public Game start(Integer rows, Integer columns, Integer mines) {
        Game game = new Game();
        game.setRows(rows);
        game.setColumns(columns);
        game.setMines(mines);
        game.setCreatedAt(new Date());
        gameRepository.save(game);

        return game;
    }
}
