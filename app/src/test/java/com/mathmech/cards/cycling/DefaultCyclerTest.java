package com.mathmech.cards.cycling;

import com.mathmech.cards.cycling.interfaces.Cycler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DefaultCyclerTest {

    @Test(expected = NullPointerException.class)
    public void emptyCyclerTest() {
        ArrayList<Card> cards = new ArrayList<>();
        Cycler cycler = new DefaultCycler(cards);
        cycler.askForNextTip();
    }

    @Test
    public void normalCyclerTest() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("First Card", "WHY", new String[]{"Hey", "a", " ", "B"}));
        cards.add(new Card("Second Card", "WHEN", new String[]{"Hi", "ma", "", "rk"}));

        Cycler cycler = new DefaultCycler(cards);

        cycler.setNextCard();

        if (Objects.equals(cycler.getQuestion(), "WHY")) {
            assertEquals("Hey", cycler.askForNextTip());
            assertEquals("a", cycler.askForNextTip());
            assertEquals("B", cycler.askForNextTip());
            assertNull(cycler.askForNextTip());
        } else {
            assertEquals("Hi", cycler.askForNextTip());
            assertEquals("ma", cycler.askForNextTip());
            assertEquals("rk", cycler.askForNextTip());
            assertNull(cycler.askForNextTip());
        }
    }
}