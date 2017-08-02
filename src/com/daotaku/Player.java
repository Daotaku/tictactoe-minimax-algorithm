package com.daotaku;

/**
 * Created by Daotaku on 8/2/2017.
 */
public enum Player {

    COMPUTER("X"), USER("O"), NONE("-");

    private Player(String text) {
        this.text = text;
    }

    private final String text;

    @Override
    public String toString() {
        return this.text;
    }
}
