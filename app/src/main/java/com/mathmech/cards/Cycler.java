package com.mathmech.cards;

import java.util.ArrayList;
import java.util.Stack;

public class Cycler {
    private final ArrayList<Card> cards;
    private final CycleStack<Card> previous;
    private final Stack<Card> accumulatedStack = new Stack<>();
    private final Choicer default_choicer;
    public Card currentCard = null;

    public Cycler(ArrayList<Card> cards) {
        this(cards, new RandomChoicer());
    }

    public Cycler(ArrayList<Card> cards, Choicer choicer) {
        previous = new CycleStack<>(Card[].class, 5);
        this.default_choicer = choicer;
        this.cards = cards;
        setNextCard();

    }

    public void SetPreviousCard() {
        accumulatedStack.push(currentCard);
        currentCard = previous.Pop();
    }

    public void setNextCard() {
        setNextCard(default_choicer);
    }

    public void setNextCard(Choicer ch) {
        if (currentCard != null)
            previous.Push(currentCard);
        if (!accumulatedStack.isEmpty())
            currentCard = accumulatedStack.pop();
        else
            currentCard = cards.get(ch.NextIndex(cards.size()));
    }

    public Card GetCurrentCard() {
        return currentCard;
    }
}
