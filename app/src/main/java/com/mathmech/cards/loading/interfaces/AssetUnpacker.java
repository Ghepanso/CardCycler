package com.mathmech.cards.loading.interfaces;

public interface AssetUnpacker {
    void exportPacketsToMemory();

    byte[] readAll(String path);

    boolean arePacketsExported();
}