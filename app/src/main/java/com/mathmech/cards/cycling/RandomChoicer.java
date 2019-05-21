package com.mathmech.cards.cycling;

import com.mathmech.cards.cycling.interfaces.Choicer;

import java.util.Random;

public class RandomChoicer implements Choicer {
    private final Random r = new Random();

    public int nextIndex(int count) {
        return r.nextInt(count);
    }
}