package com.leito.minesweeper.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static java.util.Objects.isNull;

@Getter
@Setter
public class GameDto {
    private Long id;
    private Long playedTime;
    private Date createdAt;
    private Integer rows;
    private Integer columns;
    private Integer mines;
    private Boolean won;

    public GameDto(Long id, Long playedTime, Date createdAt, Integer rows, Integer columns, Integer mines, Boolean won) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.won = won;
        if (isNull(playedTime)) {
            this.playedTime = new Date().getTime() - createdAt.getTime();
        } else {
            this.playedTime = playedTime;
        }
        this.createdAt = createdAt;
    }
}
