package com.leito.minesweeper.service;

import com.leito.minesweeper.dto.GameDto;
import com.leito.minesweeper.dto.PlayRequest;
import com.leito.minesweeper.game.Board;
import com.leito.minesweeper.game.Cell;
import com.leito.minesweeper.game.Play;
import com.leito.minesweeper.game.Status;
import com.leito.minesweeper.model.Game;
import com.leito.minesweeper.model.User;
import com.leito.minesweeper.repository.GameRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserService userService;

    public Game get(Long id) throws NotFoundException {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (!optionalGame.isPresent()) {
            throw new NotFoundException("Game not found");
        }
        return optionalGame.get();
    }

    public Game start(Integer rows, Integer columns, Integer mines, Long userId) throws NotFoundException {
        User user = userService.get(userId);
        Game game = new Game(rows, columns, mines, user);
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

    public Game resume(Long gameId) throws NotFoundException, OperationNotSupportedException {
        Game game = this.get(gameId);
        if (game.isPlaying()) {
            this.save(gameId);
        }
        game.setLastResume(new Date());
        gameRepository.save(game);
        return game;
    }

    public Play play(Long gameId, PlayRequest playRequest) throws NotFoundException, OperationNotSupportedException {
        Play play = new Play();
        Game game = this.get(gameId);
        if (game.isSaved()) {
            throw new OperationNotSupportedException("Game is paused");
        }
        Board board = game.getBoard();
        ArrayList<Cell> cells = board.play(playRequest.getAction(), playRequest.getRow(), playRequest.getColumn());
        play.setCells(cells);

        Boolean lost = board.hasMine(cells);
        if (lost) {
            game.lose();
            play.setStatus(Status.LOST);
            play.setCells(board.gameLost());
        } else if (board.wasWon()) {
            game.win();
            cells.addAll(board.getClosedCells());
            play.setStatus(Status.WON);
        } else {
            play.setStatus(Status.PLAYING);
        }
        gameRepository.save(game);
        return play;
    }

    public List<GameDto> getUnfinishedGamesByUserId(Long userId) {
        List<GameDto> userUnfinishedGameDtos = gameRepository.getUnfinishedGamesByUserId(userId);
        return userUnfinishedGameDtos;
    }

    public List<GameDto> getFinishedGamesByUserId(Long userId) {
        List<GameDto> userUnfinishedGames = gameRepository.getFinishedGamesByUserId(userId);
        return userUnfinishedGames;
    }
}
