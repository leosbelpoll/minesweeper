package com.leito.minesweeper.model;

import com.leito.minesweeper.game.Board;
import com.leito.minesweeper.utils.BoardJsonConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static java.util.Objects.isNull;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer mines;

    @Column
    private Integer rows;

    @Column
    private Integer columns;

    @Column
    private Long playedTime;

    @Column
    private Date lastResume;

    @Column
    private Boolean won;

    @Column
    private Boolean lost;

    @Column(name = "board", columnDefinition = "text")
    @Convert(converter = BoardJsonConverter.class)
    private Board board;

    @ManyToOne
    private User user;

    @Column
    private Date createdAt;

    public Game(Integer rows, Integer columns, Integer mines, User user) {
        this.mines = mines;
        this.rows = rows;
        this.columns = columns;
        this.won = false;
        this.lost = false;
        this.createdAt = new Date();
        this.lastResume = this.createdAt;
        this.board = new Board(rows, columns, mines);
        this.user = user;
    }

    public void setPlayedTime(Long playedTime) {
        if (isNull(this.playedTime))
            this.playedTime = 0L;
        this.playedTime = this.playedTime + playedTime;
    }

    public void lose() {
        this.setWon(false);
        this.setLastResume(null);
    }

    public void win() {
        this.setWon(true);
        this.setLastResume(null);
    }

    public boolean isSaved() {
        return !this.isPlaying();
    }

    public boolean isPlaying() {
        return !this.isFinished() && !isNull(this.getLastResume());
    }

    public boolean isFinished() {
        return this.getWon() || this.getWon();
    }
}
