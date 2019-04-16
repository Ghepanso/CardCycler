package com.mathmech.cards;

import developing.Config;

public class Card {
    public final String name;
    public final String question;
    public Config config;
    public final String[] tips;

    public Card(String name, String question, String[] tips) {
        this.name = name;
        this.question = question;
        this.tips = tips;
    }

    public String toString() {
        return name;
    }
}
