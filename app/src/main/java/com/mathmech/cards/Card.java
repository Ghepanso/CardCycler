package com.mathmech.cards;

import developing.Config;

public class Card {
    private final String name;
    private final String question;
    private Config config;
    private final String[] tips;

    public Card(String name, String question, String[] tips) {
        this.name = name;
        this.question = question;
        this.tips = tips;
    }

    public String toString() {
        return "Name: " + getName() + ", question" + getQuestion();
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String[] getTips() {
        return tips;
    }
}
