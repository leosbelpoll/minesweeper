package com.leito.minesweeper.dto;

import com.leito.minesweeper.game.Status;
import com.leito.minesweeper.model.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ResumeGameResponse extends PlayResponse {
    private Long playedTime;
    private Long id;
    private Integer rows;
    private Integer columns;
    private Integer mines;
    private Date createdAt;

    public ResumeGameResponse(Game game) {
        this.id = game.getId();
        this.rows = game.getRows();
        this.columns = game.getColumns();
        this.mines = game.getMines();
        this.createdAt = game.getCreatedAt();
        this.playedTime = game.getPlayedTime();
        this.setCells(game.getBoard().gameCellsStatus(), Status.PLAYING);
    }
}