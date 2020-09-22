package com.leito.minesweeper.controller;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/users")
@Api("User API")
public class UserController {
    @PostMapping()
    @ApiOperation(value = "Create/Login user", notes = "It creates a new User if it doesn't exist (Just for game interactivity)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
    })
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Get User summary")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
    })
    public ResponseEntity<String> userSummary(@NotNull @ApiParam("User id") @PathVariable("id") Long id) {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
