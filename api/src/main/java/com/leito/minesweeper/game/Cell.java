package com.leito.minesweeper.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private Integer row;
    private Integer column;
    private Boolean isMine;
    private Boolean isOpen;
    private Integer numAdjacentMines;
    private Boolean isFlag;
    private Boolean treadMine;

    public Cell(Integer row, Integer column) {
        this.row = row;
        this.column = column;
        this.isOpen = false;
        this.isFlag = false;
        this.isMine = false;
        this.numAdjacentMines = 0;
        this.treadMine = false;
    }

    public Cell() {
    }

    public void putMine() {
        isMine = true;
        this.numAdjacentMines = 0;
    }

    public void addMineAdjacent() {
        this.numAdjacentMines++;
    }
}
