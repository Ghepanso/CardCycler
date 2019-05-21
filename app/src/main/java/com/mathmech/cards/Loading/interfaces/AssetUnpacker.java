package com.mathmech.cards.Loading.interfaces;

public interface AssetUnpacker {
    void exportPacketsToMemory();

    byte[] readAll(String path);

    boolean arePacketsExported();
}