package com.example.cardgame.model;

public enum Suit implements GenerateRandomSuit {
    CLUB, DIAMOND, HEART, SPADE;

    public static Suit generateRandom() {
                                    //0-0.9999         4    =====   0 - 3
        int randomNumber = (int) (Math.random()*values().length);
        return values()[randomNumber];
    }
}
