package com.mathmech.cards.old;

import java.util.Random;

public class RandomChoicer implements Choicer {
    private final Random r = new Random();

    public int NextIndex(int count) {
        return r.nextInt(count);
    }
}