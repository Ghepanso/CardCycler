package com.mathmech.cards.cycling;

public class Card
{
    private String name;
    private String question;
    private String[] tips;

    public Card(String name, String question, String[] tips)
    {
        this.name = name;
        this.question = question;
        this.tips = tips;
    }

    public String getName()
    {
        return name;
    }

    public String getQuestion()
    {
        return question;
    }

    public int getTipsLength()
    {
        return tips.length;
    }

    public String getTip(int index)
    {
        if(index > 0 && index < tips.length-1)
            return tips[index];
        else return null; // TODO throw ex instead
    }
}
