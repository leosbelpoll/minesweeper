package com.leito.minesweeper.dto;

import com.leito.minesweeper.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginResponse {
    private Long id;
    private String username;

    public UserLoginResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
