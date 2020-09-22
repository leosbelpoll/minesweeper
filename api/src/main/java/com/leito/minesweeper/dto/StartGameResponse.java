package com.leito.minesweeper.dto;

import com.leito.minesweeper.model.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StartGameResponse {
    private Long id;
    private Integer rows;
    private Integer columns;
    private Integer mines;
    private Date createdAt;

    public StartGameResponse(Game game) {
        this.id = game.getId();
        this.rows = game.getRows();
        this.columns = game.getColumns();
        this.mines = game.getMines();
        this.createdAt = game.getCreatedAt();
    }
}
