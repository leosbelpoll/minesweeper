package com.leito.minesweeper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartGameRequest {
    @Max(30)
    @NotNull
    private Integer rows;

    @Max(30)
    @NotNull
    private Integer columns;

    @NotNull
    private Integer mines;

    @AssertTrue(message = "Mines have to be less")
    private boolean getMaxMines() {
        return rows * columns > mines;
    }
}
