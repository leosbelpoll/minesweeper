package com.leito.minesweeper.repository;

import com.leito.minesweeper.dto.GameDto;
import com.leito.minesweeper.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT new com.leito.minesweeper.dto.GameDto(g.id, g.playedTime, g.createdAt, g.rows, g.columns, g.mines, g.won) from Game g where g.user.id = ?1 and g.won = false and g.lost = false ORDER BY g.createdAt DESC")
    List<GameDto> getUnfinishedGamesByUserId(Long userId);

    @Query("SELECT new com.leito.minesweeper.dto.GameDto(g.id, g.playedTime, g.createdAt, g.rows, g.columns, g.mines, g.won) from Game g where g.user.id = ?1 and (g.won = true or g.lost = true) ORDER BY g.createdAt DESC")
    List<GameDto> getFinishedGamesByUserId(Long userId);
}
