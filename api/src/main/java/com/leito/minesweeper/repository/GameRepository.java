package com.leito.minesweeper.repository;

import com.leito.minesweeper.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
