package com.mathmech.cards.cycling;

import java.util.ArrayList;

public class Packet {
    public final String name;
    private final ArrayList<Card> cards;

    public Packet(String name, ArrayList<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}