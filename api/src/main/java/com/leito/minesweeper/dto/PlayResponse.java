package com.leito.minesweeper.dto;

import com.leito.minesweeper.game.Cell;
import com.leito.minesweeper.game.Status;
import com.leito.minesweeper.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Getter
@Setter
public class PlayResponse {
    private Status status = Status.PLAYING;
    private ArrayList<Map<String, Object>> cells;

    public PlayResponse() {
        this.cells = new ArrayList<>();
    }

    public void setCells(ArrayList<Cell> cells, Status status) {
        cells.forEach(cell -> {
            this.addCell(cell, status);
        });
        this.setStatus(status);
    }

    public void addCell(Cell cell, Status status) {
        if (isNull(this.cells)) {
            this.cells = new ArrayList<>();
        }
        Map<String, Object> cellMap = new HashMap<>();
        this.setCellCoordinates(cellMap, cell);
        if (cell.getIsMine() && Status.LOST == status) {
            cellMap.put(Constants.MINE, cell.getIsMine());
            cellMap.put(Constants.FLAG, cell.getIsFlag());
            cellMap.put(Constants.TREAD_MINE, cell.getTreadMine());
        } else if (Status.LOST == status && cell.getIsFlag()) {
            cellMap.put(Constants.WRONG_FLAG, true);
            cellMap.put(Constants.FLAG, cell.getIsFlag());
        } else {
            if (!cell.getIsOpen()) {
                cellMap.put(Constants.FLAG, cell.getIsFlag());
                if (Status.WON == status)
                    cellMap.put(Constants.FLAG, true);
            } else {
                cellMap.put(Constants.ADJACENTS, cell.getNumAdjacentMines());
            }
        }
        this.cells.add(cellMap);
    }

    private void setCellCoordinates(Map<String, Object> cellMap, Cell cell) {
        cellMap.put(Constants.ROW, cell.getRow());
        cellMap.put(Constants.COLUMN, cell.getColumn());
        cellMap.put(Constants.OPEN, cell.getIsOpen());

    }
}
