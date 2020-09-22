package com.leito.minesweeper.game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static java.util.Objects.isNull;

@Getter
@Setter
public class Board {
    private Integer rows;
    private Integer columns;
    private Integer mines;
    private Cell[][] cells;

    public Board() {
    }

    public Board(Integer rows, Integer columns, Integer mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.createBoard();
    }

    public Integer Total() {
        return this.getRows() * this.getColumns();
    }

    public void createBoard() {
        this.cells = new Cell[rows][columns];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
        this.plantMine();
    }

    private void plantMine() {
        HashSet<Integer> positions = this.generateMines();
        positions.forEach(position -> {
            Coordinate coordinate = this.getCoordenate(position);
            Cell cellWithMine = this.getCell(coordinate.getRow(), coordinate.getColumn());
            cellWithMine.putMine();

            Integer row = cellWithMine.getRow();
            Integer column = cellWithMine.getColumn();

            this.adjustAdjacent(row + 1, column);
            this.adjustAdjacent(row - 1, column);
            this.adjustAdjacent(row, column + 1);
            this.adjustAdjacent(row, column - 1);
            this.adjustAdjacent(row + 1, column + 1);
            this.adjustAdjacent(row - 1, column - 1);
            this.adjustAdjacent(row - 1, column + 1);
            this.adjustAdjacent(row + 1, column - 1);
        });
    }

    private void adjustAdjacent(Integer row, Integer column) {
        if (isCoordinateValid(row, column) && !cells[row][column].getIsMine()) {
            cells[row][column].addMineAdjacent();
        }
    }

    private Boolean isCoordinateValid(Integer row, Integer column) {
        return row >= 0 && row < this.getRows() && column >= 0 && column < this.getColumns();
    }

    private HashSet<Integer> generateMines() {
        Random random = new Random();
        HashSet<Integer> positionMines = new HashSet<>();
        while (positionMines.size() < this.getMines()) {
            Integer i = random.nextInt(this.Total());
            positionMines.add(i);
        }
        return positionMines;
    }

    private Coordinate getCoordenate(Integer position) {
        Integer row = position / this.getColumns();
        Integer column = position % this.getColumns();
        Coordinate coordinate = new Coordinate(row, column);
        return coordinate;
    }

    public ArrayList<Cell> gameCellsStatus() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                Cell cell = this.getCell(i, j);
                if (cell.getIsOpen() || cell.getIsFlag())
                    cells.add(cell);
            }
        }
        return cells;
    }

    public ArrayList<Cell> gameLost() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                Cell cell = this.getCell(i, j);
                if (cell.getIsMine()) {
                    if (!cell.getIsFlag())
                        cell.setIsOpen(true);
                    cells.add(cell);
                } else {
                    if (!cell.getIsOpen() && cell.getIsFlag()) {
                        cells.add(cell);
                    }
                }
            }
        }
        return cells;
    }

    public ArrayList<Cell> getClosedCells() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                Cell cell = this.getCell(i, j);
                if (!cell.getIsOpen())
                    cells.add(cell);
            }
        }
        return cells;
    }

    public ArrayList<Cell> play(Action action, Integer row, Integer column) {
        ArrayList<Cell> cells = new ArrayList<>();
        if (action.equals(Action.FLAG)) {
            Cell cell = this.getCell(row, column);
            if (!cell.getIsOpen()) {
                cells.add(this.setCellFlag(row, column));
            }
            return cells;
        } else if (action.equals(Action.REVEAL)) {
            cells = this.revealCells(row, column);
            return cells;
        }
        return null;
    }

    private Cell getCell(Integer row, Integer column) {
        if (this.isCoordinateValid(row, column))
            return this.getCells()[row][column];
        return null;
    }

    private Cell setCellFlag(Integer row, Integer column) {
        Cell cell = this.getCell(row, column);
        if (!cell.getIsOpen()) {
            cell.setIsFlag(!cell.getIsFlag());
            return cell;
        }
        return null;
    }

    private ArrayList<Cell> revealCells(Integer row, Integer column) {
        ArrayList<Cell> cells = new ArrayList<>();
        Cell _cell = this.getCell(row, column);
        if (_cell.getIsFlag())
            return cells;
        if (!_cell.getIsOpen()) {
            this.revealCell(cells, row, column);
        } else if (_cell.getIsOpen() && _cell.getNumAdjacentMines() > 0) {
            this.revealAdjacentCells(cells, row, column);
        }

        return cells;
    }

    private void revealAdjacentCells(ArrayList<Cell> cells, Integer row, Integer column) {
        ArrayList<Coordinate> adjacents = new ArrayList<>();
        ArrayList<Cell> cellAdjacent = new ArrayList<>();
        adjacents.add(new Coordinate(row + 1, column));
        adjacents.add(new Coordinate(row - 1, column));
        adjacents.add(new Coordinate(row, column + 1));
        adjacents.add(new Coordinate(row, column - 1));
        adjacents.add(new Coordinate(row + 1, column + 1));
        adjacents.add(new Coordinate(row - 1, column - 1));
        adjacents.add(new Coordinate(row + 1, column - 1));
        adjacents.add(new Coordinate(row - 1, column + 1));
        adjacents.forEach(coordinate -> {
            Cell _cell = this.getCell(coordinate.getRow(), coordinate.getColumn());
            if (!isNull(_cell)) {
                cellAdjacent.add(_cell);
            }
        });
        Long countMines = cellAdjacent.stream().filter(c -> c.getIsMine()).count();
        Long countFlags = cellAdjacent.stream().filter(c -> c.getIsFlag()).count();
        if (countFlags >= countMines) {
            adjacents.forEach(coordinate -> {
                this.revealCell(cells, coordinate.getRow(), coordinate.getColumn());
            });
        }
    }

    private void revealCell(ArrayList<Cell> cells, Integer row, Integer column) {
        Cell _cell = this.getCell(row, column);
        if (!isNull(_cell)) {
            Boolean visited = cells.stream().anyMatch(c -> c.getRow() == row && c.getColumn() == column);

            if (!isNull(_cell) && !_cell.getIsOpen() && !_cell.getIsFlag()) {
                _cell.setIsOpen(true);
                cells.add(_cell);

                if (_cell.getIsMine()) {
                    _cell.setTreadMine(true);
                }

                if (_cell.getNumAdjacentMines() == 0 && !_cell.getIsMine() && !visited) {
                    this.revealCell(cells, row + 1, column);
                    this.revealCell(cells, row - 1, column);
                    this.revealCell(cells, row, column + 1);
                    this.revealCell(cells, row, column - 1);
                    this.revealCell(cells, row + 1, column + 1);
                    this.revealCell(cells, row - 1, column - 1);
                    this.revealCell(cells, row + 1, column - 1);
                    this.revealCell(cells, row - 1, column + 1);
                }
            }
        }
    }

    public Boolean hasMine(ArrayList<Cell> cells) {
        return cells.stream().anyMatch(cell -> cell.getIsMine() && cell.getIsOpen());
    }

    public Boolean wasWon() {
        Integer count = 0;
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                Cell _cell = this.getCell(i, j);
                if (!isNull(_cell) && !_cell.getIsOpen())
                    count++;
            }
        }
        return this.getMines() == count;
    }
}
