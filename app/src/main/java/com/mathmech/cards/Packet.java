package com.mathmech.cards;

import java.util.ArrayList;

public class Packet {
    public final String name;
    public boolean loaded = false;

    public final String folder;
    public Node root;

    public Packet(String name, String folder) {
        this.name = name;
        this.folder = folder;
    }

    public void loadIfUnloaded(AssetUnpacker unpacker) {
        if (!loaded) {
            loaded = true;
        }
    }

    public ArrayList<Card> getCards(AssetUnpacker unpacker) {
        loadIfUnloaded(unpacker);
        return unpacker.getLeaves(folder);
    }

    public String toString() {
        return name;
    }
}
