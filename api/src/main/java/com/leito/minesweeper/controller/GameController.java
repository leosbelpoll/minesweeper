package com.leito.minesweeper.controller;

import com.leito.minesweeper.dto.StartGameRequest;
import com.leito.minesweeper.dto.StartGameResponse;
import com.leito.minesweeper.model.Game;
import com.leito.minesweeper.service.GameService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/games")
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping(path = "start")
    @ApiOperation(value = "Start a new game")
    public ResponseEntity<StartGameResponse> start(@Valid @RequestBody StartGameRequest startGameRequest) {
        Game game = this.gameService.start(startGameRequest.getRows(), startGameRequest.getColumns(), startGameRequest.getMines());
        StartGameResponse response = new StartGameResponse(game);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/resume")
    public ResponseEntity<String> resume() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/save")
    public ResponseEntity<String> save() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/play")
    public ResponseEntity<String> play() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
