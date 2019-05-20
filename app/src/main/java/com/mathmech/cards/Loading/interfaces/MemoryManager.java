package com.mathmech.cards.Loading.interfaces;

import java.util.List;

public interface MemoryManager
{
    List<String> listDirectory(String relativePathToDir);
    List<Byte> readFile(String relativePathToFile, String fileName);
    void writeFile(String relativePathToFile, String fileName, List<Byte> toWrite);
}
