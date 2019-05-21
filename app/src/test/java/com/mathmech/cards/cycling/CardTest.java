package com.mathmech.cards.cycling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

    @Test
    public void fixTipsTest() {
        Card card = new Card("name", "question", new String[]{"a", "\n", " ", "A", "."});
        assertEquals(3, card.getTipsLength());
        assertEquals("a", card.getTip(0));
        assertEquals("A", card.getTip(1));
    }
}