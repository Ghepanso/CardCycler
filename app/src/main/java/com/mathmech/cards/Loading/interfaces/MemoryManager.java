package com.mathmech.cards.Loading.interfaces;

import java.util.List;

public interface MemoryManager {
    List<String> listDirectory(String relativePathToDir);

    byte[] readFile(String relativePathToFile, String fileName);

    void writeFile(String relativePathToFile, String fileName, byte[] toWrite);
}