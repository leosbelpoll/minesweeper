import React from "react";
import classNames from "classnames";

import "./Cell.scss";

const Cell = ({
    adjacents,
    open,
    mine,
    flag,
    row,
    column,
    onGamePlay,
    wrongFlag,
}) => {
    const classes = classNames("game-cell", {
        "game-open-cell": open,
        "game-mine-cell": mine && !flag,
        "game-wrong-flag-cell": wrongFlag,
        "game-flag-cell": !!flag,
    });

    const onPlay = (e, action) => {
        e.preventDefault();
        onGamePlay({
            row,
            column,
            action,
        });
    };

    return (
        <div
            data-testid="game-cell"
            className={classes}
            onClick={(e) => onPlay(e, "REVEAL")}
            onContextMenu={(e) => onPlay(e, "FLAG")}
        >
            {open && !mine && adjacents !== 0 && adjacents}
            {flag && <i className="glyphicon glyphicon-flag"></i>}
            {mine && !flag && (
                <span role="img" aria-label="Mine icon">
                    🧨
                </span>
            )}
        </div>
    );
};

export default Cell;
