package com.leito.minesweeper.game;

public enum Action {
    FLAG("FLAG"),
    REVEAL("REVEAL");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public static Action fromValue(String value) {
        for (Action action : values()) {
            if (action.value.equalsIgnoreCase(value)) {
                return action;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }
}
