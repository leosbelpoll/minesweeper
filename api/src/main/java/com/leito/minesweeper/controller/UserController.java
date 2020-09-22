package com.leito.minesweeper.controller;

import com.leito.minesweeper.dto.UserLoginRequest;
import com.leito.minesweeper.dto.UserLoginResponse;
import com.leito.minesweeper.model.User;
import com.leito.minesweeper.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/users")
@Api("User API")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    @ApiOperation(value = "Create/Login user", notes = "It creates a new User if it doesn't exist (Just for game interactivity)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
    })
    public ResponseEntity<UserLoginResponse> login(@Valid @ApiParam(required = true, value = "User data") @RequestBody UserLoginRequest userRequest) {
        User user = userService.login(userRequest.getUsername());
        UserLoginResponse userLoginResponse = new UserLoginResponse(user);
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
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
