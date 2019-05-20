package com.mathmech.cards.Loading.interfaces;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface MemoryManager
{
    ArrayList<String> listDirectory(String relativePathToDir);
    ArrayList<Byte> readFile(String relativePathToFile, String fileName);
    void writeFile(String relativePathToFile, String fileName, ArrayList<Byte> toWrite);
}
