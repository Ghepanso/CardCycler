package com.mathmech.cards.old;

import android.content.res.AssetManager;

import com.mathmech.cards.old.Card;
import com.mathmech.cards.old.Packet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import developing.Config;

public class AssetUnpacker
{
    static final String sep = "/"; //TODO prove it
    static final String configNameExt = ".pck";
    static final String leafNameExt = ".txt";
    private AssetManager manager;

    public AssetUnpacker(AssetManager manager)
    {
        this.manager = manager;
    }

    public AssetUnpacker()
    {

    }

    public String[] list(String path)
    {
        String a = "";
<<<<<<< HEAD:app/src/main/java/com/mathmech/cards/AssetUnpacker.java
        
        try {
=======
        try
        {
>>>>>>> 4b85ad3241c21aa544aaae7bf0ef04ae05202ed1:app/src/main/java/com/mathmech/cards/old/AssetUnpacker.java
            return manager.list(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new String[0];
    }

    private static String getLastNameInPath(String path)
    {
        int lastIndexOf = path.lastIndexOf(sep);
        if (lastIndexOf == -1)
        {
            return "";
        }
        return path.substring(lastIndexOf + 1);
    }

    private static String getFileExtension(String fileName)
    {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1)
        {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }

    protected static Config getConfig(String fileName)
    {
        return null; // TODO: stub method

    }

    public boolean isDirectory(String path)
    { //TODO: make betta
        String ext = getFileExtension(path);
        return (ext.equals("") || (!ext.equals(configNameExt) && !ext.equals(leafNameExt)));
    }

    public HashMap<String, Packet> extractPackets(String packetsFolderPath)
    {
        HashMap<String, Packet> result = new HashMap<>();
        String[] nestedFiles = list(packetsFolderPath);
        for (String fileName : nestedFiles)
            if (isDirectory(fileName))
                result.put(fileName, new Packet(fileName, packetsFolderPath + sep + fileName));
        return result;
    }


    public ArrayList<Card> getLeaves(String root)
    {
        Stack<String> folderStack = new Stack<>();
        ArrayList<Card> result = new ArrayList<>();
        folderStack.push(root);
        while (!folderStack.isEmpty())
        {
            String folder = folderStack.pop();
            String[] children = list(folder);

            for (String child : children)
            {
                if (isDirectory(child))
                    folderStack.push(folder + sep + child);
                else
                {
                    String ext = getFileExtension(child);
                    if (ext.equals(leafNameExt))
                        result.add(readLeaf(folder + sep + child));
                }
            }
        }
        return result;
    }

    private Card readLeaf(String path)
    {
        String fileName = getLastNameInPath(path);
        try
        {
            InputStream stream = manager.open(path, AssetManager.ACCESS_RANDOM);

            Scanner s = new Scanner(stream).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            String[] lines = result.split("\n");
            int tipsCount = lines.length - 1;
            String[] tips = new String[tipsCount];
            for (int i = 0; i < tipsCount; i++)
            {
                tips[i] = lines[i + 1].trim();
            }
            ArrayList<String> tipsList = new ArrayList<>(); //TODO: delete array/list
            for (int i = 0; i < tips.length; i++)
            {
                if (tips[i].equals(""))
                    continue;
                else
                    tipsList.add(tips[i]);
            }
            return new Card(fileName, lines[0].trim(), tipsList.toArray(new String[tipsList.size()]));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
