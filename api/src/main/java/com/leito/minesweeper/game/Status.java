package com.leito.minesweeper.game;


public enum Status {
    LOST("LOST"),
    PLAYING("PLAYING"),
    WON("WON");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public static Status fromValue(String value) {
        for (Status action : values()) {
            if (action.value.equalsIgnoreCase(value)) {
                return action;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }
}
