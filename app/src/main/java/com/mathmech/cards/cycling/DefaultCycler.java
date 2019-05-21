package com.mathmech.cards.cycling;

import com.mathmech.cards.cycling.interfaces.Choicer;
import com.mathmech.cards.cycling.interfaces.Cycler;

import java.util.ArrayList;

public class DefaultCycler implements Cycler {
    private ArrayList<Card> allCards;
    private int cardsCount;
    private Card currentCard;
    private Choicer choicer;
    private int tipsGiven = 0;

    private DefaultCycler(ArrayList<Card> cards, Choicer choicer) {
        allCards = cards;
        cardsCount = cards.size();
        this.choicer = choicer;
    }

    public DefaultCycler(ArrayList<Card> cards) {
        this(cards, new RandomChoicer());
    }

    public String getQuestion() {
        if (currentCard != null)
            return currentCard.getQuestion();
        else
            return null;
    }

    public String askForNextTip() {
        if (tipsGiven < currentCard.getTipsLength())
            return currentCard.getTip(tipsGiven++);
        else return null;
    }

    public void setNextCard() {
        int nextCardIndex = choicer.nextIndex(cardsCount);
        currentCard = allCards.get(nextCardIndex);
        tipsGiven = 0;
    }
}