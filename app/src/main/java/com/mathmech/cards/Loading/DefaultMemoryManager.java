package com.mathmech.cards.Loading;
import com.mathmech.cards.Loading.interfaces.MemoryManager;
import java.util.ArrayList;

//TODO MEMORY MANAGER
public class DefaultMemoryManager implements MemoryManager
{
    public ArrayList<String> listDirectory(String relativePathToDir)
    {
        return null;
    }

    public ArrayList<Byte> readFile(String relativePathToFile, String fileName)
    {
        return null;
    }

    public void writeFile(String relativePathToFile, String fileName, ArrayList<Byte> toWrite)
    {

    }
}
