package com.example.cardgame.controller;

import com.example.cardgame.model.Card;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
public class CardController {
    Card card;

    @GetMapping("start-round")
    public Card startRound() {
        if (card == null) {
            card = new Card();
        }
        return card;
    }

    @GetMapping("round-response/{choice}")
    public boolean roundResponse(@PathVariable String choice) {
        Card newCard = new Card();
        String result;
        if (newCard.getValue() < card.getValue()) {
            result = "lower";
        } else if (newCard.getValue() > card.getValue()) {
            result = "higher";
        } else {
            result = "equal";
        }
        card = newCard;
        return choice.equals(result);
    }

    @GetMapping("timeout")
    public void timeout() {
        card = new Card();
    }
}
