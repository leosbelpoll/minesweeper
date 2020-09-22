package com.leito.minesweeper.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    private Integer row;
    private Integer column;

    public Coordinate(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }
}
