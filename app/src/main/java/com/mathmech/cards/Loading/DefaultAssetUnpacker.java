package com.mathmech.cards.Loading;
import android.content.res.AssetManager;

import com.mathmech.cards.Loading.interfaces.AssetUnpacker;
import com.mathmech.cards.Loading.interfaces.MemoryManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DefaultAssetUnpacker implements AssetUnpacker
{
    final String defaultAssetsFolder = "Packets";
    final String packetExtension = ".pkt";
    final String pathSep = File.separator;
    private AssetManager manager;


    public DefaultAssetUnpacker(AssetManager manager)
    {
        this.manager = manager;
    }

    private static String getFileExtension(String fileName)
    {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) return ""; // empty extension
        else return fileName.substring(lastIndexOf);
    }

    private void saveInMemory(String pathToFile, String fileName, String savePath)
    {
        try
        {
            //TODO: maybe should we load it into memory now?
            InputStream stream = manager.open(pathToFile);
            byte[] buffer = new byte[stream.available()];
            int readed = stream.read(buffer);

            MemoryManager manager = new DefaultMemoryManager();
            manager.writeFile(savePath, fileName, buffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void exportPacketsToMemory()
    {
        String[] nestedFiles;
        try
        {
            nestedFiles = manager.list(defaultAssetsFolder);
            if (nestedFiles != null)
                for (String fileName : nestedFiles)
                {
                    String ext = getFileExtension(fileName);
                    if(ext.equals(packetExtension))
                        saveInMemory(defaultAssetsFolder + pathSep + fileName,
                                fileName, defaultAssetsFolder + pathSep);
                }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public byte[] readAll(String path)
    {
        //TODO readAll()
        return new byte[0];
    }

    public boolean arePacketsExported()
    {
        //TODO arePacketsExported()
        return false;
    }
}
