package com.leito.minesweeper.dto;

import com.leito.minesweeper.game.Action;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlayRequest {
    @NotNull
    private Action action;

    @NotNull
    private Integer row;

    @NotNull
    private Integer column;
}
