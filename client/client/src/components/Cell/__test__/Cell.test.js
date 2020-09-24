import React from "react";
import ReactDOM from "react-dom";
import { render } from "@testing-library/react";

import Cell from "../index";

it("renders without crashing", () => {
    ReactDOM.render(<Cell />, document.createElement("div"));
});

test("renders closed Cell", () => {
    const cell = {
        row: 0,
        column: 0,
    };
    const { getByTestId } = render(<Cell {...cell} />);
    const gameCell = getByTestId("game-cell");
    expect(gameCell).toHaveClass("game-cell");
    expect(gameCell).not.toHaveClass("game-open-cell");
    expect(gameCell).not.toHaveClass("game-mine-cell");
    expect(gameCell).not.toHaveClass("game-wrong-flag-cell");
    expect(gameCell).not.toHaveClass("game-flag-cell");
});

test("renders opened Cell", () => {
    const cell = {
        row: 0,
        column: 0,
        open: true,
        adjacents: 1,
    };
    const { getByTestId } = render(<Cell {...cell} />);
    const gameCell = getByTestId("game-cell");
    expect(gameCell).toHaveClass("game-cell");
    expect(gameCell).toHaveClass("game-open-cell");
    expect(gameCell).not.toHaveClass("game-mine-cell");
    expect(gameCell).not.toHaveClass("game-wrong-flag-cell");
    expect(gameCell).not.toHaveClass("game-flag-cell");
    expect(gameCell).toHaveTextContent("1");
});

test("renders flag Cell", () => {
    const cell = {
        row: 0,
        column: 0,
        flag: true,
    };
    const { getByTestId } = render(<Cell {...cell} />);
    const gameCell = getByTestId("game-cell");
    expect(gameCell).toHaveClass("game-cell");
    expect(gameCell).not.toHaveClass("game-open-cell");
    expect(gameCell).not.toHaveClass("game-mine-cell");
    expect(gameCell).not.toHaveClass("game-wrong-flag-cell");
    expect(gameCell).toHaveClass("game-flag-cell");
});

test("renders mine Cell", () => {
    const cell = {
        row: 0,
        column: 0,
        mine: true,
        open: true,
    };
    const { getByTestId } = render(<Cell {...cell} />);
    const gameCell = getByTestId("game-cell");
    expect(gameCell).toHaveClass("game-cell");
    expect(gameCell).toHaveClass("game-open-cell");
    expect(gameCell).toHaveClass("game-mine-cell");
    expect(gameCell).not.toHaveClass("game-wrong-flag-cell");
    expect(gameCell).not.toHaveClass("game-flag-cell");
});

test("renders mine Cell", () => {
    const cell = {
        row: 0,
        column: 0,
        wrongFlag: true,
        mine: true,
        open: true,
    };
    const { getByTestId } = render(<Cell {...cell} />);
    const gameCell = getByTestId("game-cell");
    expect(gameCell).toHaveClass("game-cell");
    expect(gameCell).toHaveClass("game-open-cell");
    expect(gameCell).toHaveClass("game-mine-cell");
    expect(gameCell).toHaveClass("game-wrong-flag-cell");
    expect(gameCell).not.toHaveClass("game-flag-cell");
});
