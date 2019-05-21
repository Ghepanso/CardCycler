package com.mathmech.cards.cycling;

import android.content.res.AssetManager;

import com.mathmech.cards.Loading.interfaces.AssetUnpacker;
import com.mathmech.cards.Loading.DefaultAssetUnpacker;
import com.mathmech.cards.Loading.DefaultLoader;
import com.mathmech.cards.cycling.interfaces.Holder;
import com.mathmech.cards.Loading.interfaces.Loader;

import java.util.HashMap;
import java.util.Set;

public class DefaultHolder implements Holder
{
    //static final String defaultPacketFolderName = "Packets";
    private HashMap<String, Packet> packets;

    public DefaultHolder(AssetManager manager)
    {
        packets = getPackets(manager);

    }

    // region getPackets
    private HashMap<String, Packet> getPackets(AssetManager manager)
    {
        exportAssetsIfNotExported(manager);
        return loadPacketsFromMemory();
    }

    private void exportAssetsIfNotExported(AssetManager manager)
    {
        AssetUnpacker unpacker = new DefaultAssetUnpacker(manager);
        if(!unpacker.arePacketsExported())
            unpacker.exportPacketsToMemory();
    }

    private  HashMap<String, Packet> loadPacketsFromMemory()
    {
        Loader loader = new DefaultLoader();
        return loader.loadPacketsFromMemory();
    }

    // endregion

    public Set<String> getPacketNames()
    {

        return packets.keySet();
    }

    public Packet getPacketByName(String name)
    {
        name += ".pkt";
        if(packets.containsKey(name))
            return packets.get(name);
        else return null; //TODO throw exception
    }
}
