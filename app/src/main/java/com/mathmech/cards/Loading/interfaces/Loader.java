package com.mathmech.cards.Loading.interfaces;

import com.mathmech.cards.cycling.Packet;

import java.util.HashMap;

public interface Loader {
    HashMap<String, Packet> loadPacketsFromMemory();
}