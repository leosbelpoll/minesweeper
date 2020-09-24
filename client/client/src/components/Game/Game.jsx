import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import { useHistory } from "react-router-dom";
import classNames from "classnames";

import Cell from "components/Cell";
import { startGame } from "redux/actions/game";
import { play, saveGame } from "redux/actions/game";

import "./Game.scss";
import { getTimeFormat } from "utils/dateTime";

const Game = ({ user, config, game, startGame, saveGame, play, location }) => {
    const history = useHistory();
    const [timer, setTimer] = useState();
    const { gameId: gameIdFromLocation } = location;
    const { rows, columns, mines } = config;
    const { id: gameId, won, lost, playedTime } = game;
    const { id: userId } = user;
    const isOver = won || lost;

    const onPlay = (options) => {
        if (!isOver) {
            play(gameId, options);
        }
    };

    const onSave = (e) => {
        e.preventDefault();
        saveGame(gameId);
        history.push("/");
    };

    const onCreate = (e) => {
        e.preventDefault();
        createBoard();
    };

    const possibleMines =
        game.mines - game.board.filter((cell) => cell.flag).length;

    const getUpdatedBoard = () => {
        const { board: cells } = game;
        const gameBoarddd = [];
        for (let i = 0; i < rows; i++) {
            const rowCells = [];
            for (let j = 0; j < columns; j++) {
                let cell = cells.find(
                    (cell) => cell.row === i && cell.column === j
                ) || { row: i, column: j };
                rowCells.push(
                    <Cell key={`${i}-${j}`} {...cell} onGamePlay={onPlay} />
                );
            }
            gameBoarddd.push(
                <div key={i} className="game-row-cells">
                    {rowCells}
                </div>
            );
        }

        return gameBoarddd;
    };

    const createBoard = () => {
        startGame({
            userId,
            rows,
            columns,
            mines,
        });
    };

    useEffect(() => {
        if (!gameIdFromLocation) {
            createBoard();
        }
    }, [gameIdFromLocation]);

    useEffect(() => {
        setTimer(playedTime);
        const timerInterval = setInterval(() => {
            setTimer((prevTimer) => prevTimer + 1000);
        }, 1000);
        if (isOver) {
            clearInterval(timerInterval);
        }
        return () => {
            clearInterval(timerInterval);
        };
    }, [isOver, playedTime]);

    return (
        <div className="game-component">
            <div className="text-center">
                <div className="row">
                    <div className="col-xs-12">
                        <div className="game-cells">
                            <div className="game-header">
                                <a
                                    href="#stop"
                                    className={classNames("btn btn-success", {
                                        "btn-disabled": isOver,
                                    })}
                                    onClick={onSave}
                                >
                                    <i className="glyphicon glyphicon-floppy-disk"></i>{" "}
                                    Save
                                </a>
                                <a
                                    href="#new"
                                    className="btn btn-primary"
                                    onClick={onCreate}
                                >
                                    <i className="glyphicon glyphicon-refresh"></i>{" "}
                                    New game
                                </a>
                                <span className="game-header-info">
                                    <span role="img" aria-label="Mine icon">
                                        🧨 {possibleMines}
                                    </span>
                                </span>
                                <span className="game-header-info">
                                    {getTimeFormat(timer)}
                                </span>
                            </div>
                            {getUpdatedBoard()}
                        </div>
                        {won && (
                            <div className="col-xs-12">
                                <h2 className="big-title-primary">
                                    You have won the game :)
                                </h2>
                            </div>
                        )}
                        {lost && (
                            <div className="col-xs-12">
                                <h2 className="big-title-error">
                                    You have lost the game :(
                                </h2>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

const mapStateToProps = (state) => {
    return {
        user: state.user,
        config: state.config,
        game: state.game,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        play: (id, options) => dispatch(play(id, options)),
        startGame: (options) => dispatch(startGame(options)),
        saveGame: (id) => dispatch(saveGame(id)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Game);
