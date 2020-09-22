package com.leito.minesweeper.controller;

import com.leito.minesweeper.dto.*;
import com.leito.minesweeper.game.Play;
import com.leito.minesweeper.model.Game;
import com.leito.minesweeper.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/games")
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping(path = "start")
    @ApiOperation(value = "Start a new game")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Game started successfully"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<StartGameResponse> start(@Valid @RequestBody StartGameRequest startGameRequest) {
        try {
            Game game = this.gameService.start(startGameRequest.getRows(), startGameRequest.getColumns(), startGameRequest.getMines(), startGameRequest.getUserId());
            StartGameResponse response = new StartGameResponse(game);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/{id}/resume")
    @ApiOperation(value = "Resume a game")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Game resumed successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Game not found"),
            @ApiResponse(code = 412, message = "Game is already resumed")
    })
    public ResponseEntity<ResumeGameResponse> resume(@NotNull @ApiParam("Game id") @PathVariable("id") Long id) {
        try {
            Game game = gameService.resume(id);
            ResumeGameResponse resumeGameResponse = new ResumeGameResponse(game);
            return new ResponseEntity<>(resumeGameResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (OperationNotSupportedException e) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PostMapping(path = "/{id}/save")
    @ApiOperation(value = "Save a game")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Game saved successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Game not found"),
            @ApiResponse(code = 412, message = "Game is already saved")
    })
    public ResponseEntity<?> save(@NotNull @ApiParam("Game id") @PathVariable("id") Long id) {
        try {
            gameService.save(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (OperationNotSupportedException e) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PostMapping(path = "/{id}/play")
    @ApiOperation(value = "Play")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Play successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Game not found"),
            @ApiResponse(code = 412, message = "Game is paused")
    })
    public ResponseEntity<PlayResponse> play(@NotNull @ApiParam("Game id") @PathVariable("id") Long id,
                                             @Valid @ApiParam(required = true, value = "Play options") @RequestBody PlayRequest playRequest) {
        try {
            Play play = gameService.play(id, playRequest);
            PlayResponse playResponse = new PlayResponse();
            playResponse.setCells(play.getCells(), play.getStatus());
            return new ResponseEntity<>(playResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (OperationNotSupportedException e) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }
}
