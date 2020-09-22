package com.leito.minesweeper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/games")
public class GameController {

    @PostMapping(path = "start")
    public ResponseEntity<String> start() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
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
