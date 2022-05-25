package com.example.cardgame.model;

import lombok.Data;

@Data
public class Card {
    private final Suit suit;
    private final Rank rank;
    private final int value;

    public Card() {
        this.suit = Suit.generateRandom();
        this.rank = Rank.generateRandom();
        this.value = getValueByRank();
    }

    private int getValueByRank() {
//        if (rank == Rank.TWO) {
//            return 2;
//        } else if (rank == Rank.THREE) {
//            return 3;
//        } else if (rank == Rank.THREE) {
//            return 3;
//        } else if (rank == Rank.THREE) {
//            return 3;
//        } else if (rank == Rank.THREE) {
//            return 3;
//        } else if (rank == Rank.THREE) {
//            return 3;
//        } else if (rank == Rank.TEN || rank == Rank.JACK
//                || rank == Rank.QUEEN || rank == Rank.KING || rank == Rank.ACE) {
//            return 10;
//        } else {
//            return 0;
//        }

        switch (rank) {
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGTH:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
            case ACE:
                return 10;
            default:
                return 0;
        }
    }


}
