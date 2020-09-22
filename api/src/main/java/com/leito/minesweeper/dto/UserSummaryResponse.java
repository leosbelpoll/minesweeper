package com.leito.minesweeper.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSummaryResponse {
    private List<GameDto> unfinishedGames;
    private List<GameDto> finishedGames;
}
