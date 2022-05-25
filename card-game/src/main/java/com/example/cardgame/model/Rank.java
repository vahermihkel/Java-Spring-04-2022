package com.example.cardgame.model;

public enum Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGTH, NINE, TEN, JACK, QUEEN, KING, ACE;

    public static Rank generateRandom() {
        int randomNumber = (int) (Math.random()*values().length);
        return values()[randomNumber];
    }
}
