package com.mathmech.cards;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

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

    public String[] list(String path)
    {
        String a = "";
        try
        {
            return manager.list(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new String[0];
    }

    //    File packetsFolder = new File(packetFolderName);
    //    File[] nestedFiles = packetsFolder.listFiles();
    //        if (nestedFiles != null)
    //        for (File file : nestedFiles)
    //        if (file.isDirectory())
    //        result.put(file.getName(), new Packet(file.getName(), file));
    //        return result;
    // region stuff

    public static void println(Object... tokens)
    {
        System.out.println(String.join(" ", Arrays.stream(tokens).map(Object::toString).collect(Collectors.toList())));
    }

    private static String getLastNameInPath(String path)
    {
        int lastIndexOf = path.lastIndexOf(sep);
        if(lastIndexOf == -1)
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
    // endregion

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
        ArrayList<Card> result = new ArrayList<Card>();
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


    //    private void parseLeaf(HashMap<String, Card> map, String leafPath)
//    {
//        String ext = getFileExtension(leafPath);
//        switch (ext)
//        {
//            case leafNameExt:
//                if (!map.containsKey(leafPath))
//                    map.put(leafPath, new Card(leafPath));
//                break;
//            case configNameExt:
//                if (!map.containsKey(leafPath))
//                    map.put(leafPath, new Card(leafPath));
//                else
//                {
//                    Card leaf = map.get(leafPath);
//                    if(leaf != null)
//                        leaf.config = getConfig(leafPath);
//                }
//                break;
//            default:
//                break; // TODO: throw some stuff?
//        }
//    }


    private Card readLeaf(String path)
    {
        String fileName = getLastNameInPath(path);
        try
        {
            InputStream stream = manager.open(path, AssetManager.ACCESS_RANDOM);
            //byte[] buffer = new byte[stream.available()];
            //int bytesReaded = stream.read(buffer, 0, stream.available());

            Scanner s = new Scanner(stream).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            String[] lines = result.split("\n");
            int tipsCount = lines.length - 1;
            String[] tips = new String[tipsCount];
            for (int i = 0; i < tipsCount; i++)
            {
                tips[i] = lines[i + 1].trim();
            }
            return new Card(fileName, lines[0].trim(), tips);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
