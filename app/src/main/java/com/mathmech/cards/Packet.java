package com.mathmech.cards;

import java.io.File;
import java.util.ArrayList;

public class Packet
{
    public String name;
    public boolean loaded = false;

    //public File folder;
    public String folder;
    public Node root;

//    public Packet(String name, File folder) {
//        this.name = name;
//        this.folder = folder;
//    }

    public Packet(String name, String folder)
    {
        this.name = name;
        this.folder = folder;
    }

    public void loadIfUnloaded(AssetUnpacker unpacker)
    {
        if (!loaded)
        {
            //root = FolderWalker.getNodeTree(folder);
            loaded = true;
        }
    }

    public ArrayList<Card> getCards(AssetUnpacker unpacker)
    {
        loadIfUnloaded(unpacker);
        return unpacker.getLeaves(folder);
    }

    public String toString()
    {
        return name;
    }
}
