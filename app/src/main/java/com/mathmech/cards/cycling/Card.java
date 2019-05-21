package com.mathmech.cards.cycling;

import android.support.annotation.RequiresApi;

import java.util.LinkedList;
import java.util.List;

public class Card {
    private final String name;
    private final String question;
    private final String[] tips;

    public Card(String name, String question, String[] tips) {
        this.name = name;
        this.question = question;
        this.tips = fixTips(tips);
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public int getTipsLength() {
        return tips.length;
    }

    private String[] fixTips(String[] strings) {
        List<String> res = new LinkedList<>();
        for (String string : strings) {
            if (!string.equals(""))
                res.add(string);
        }
        return res.toArray(new String[0]);
    }

    public String getTip(int index) {
        if (index > 0 && index < tips.length)
            return tips[index];
        else {
            return null;
        }
    }
}