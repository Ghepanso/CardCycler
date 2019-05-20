package com.mathmech.cards.cycling;

import com.mathmech.cards.cycling.interfaces.Cycler;

import java.util.ArrayList;

public class DefaultCycler implements Cycler
{
    //TODO CYCLER
    public DefaultCycler(ArrayList<Card> cards)
    {
        
    }

    public String getQuestion()
    {
        return null;
    }

    public boolean couldGiveTip()
    {
        return false;
    }

    public String askForTip()
    {
        return null;
    }

    public void setNextCard()
    {

    }
}
