package com.leito.minesweeper.game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Play {
    private Status status;
    private ArrayList<Cell> cells;
}
