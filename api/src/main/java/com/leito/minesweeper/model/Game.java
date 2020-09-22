package com.leito.minesweeper.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "games")
@Getter
@Setter
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
    private Date createdAt;
}
