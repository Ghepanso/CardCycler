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
    HashMap<String, Packet> packets;

    public DefaultHolder(AssetManager manager)
    {
        packets = getPackets(manager);

    }

    // region getPackets
    public HashMap<String, Packet> getPackets(AssetManager manager)
    {
        if (areAssetsExtracted())
            return loadPacketsFromMemory();
        else
            return unitePacketMaps(
                    loadPacketsFromAssets(manager),
                    loadPacketsFromMemory()
            );
    }

    public boolean areAssetsExtracted()
    {

        return true; // TODO areAssetsExtracted
    }

    @SafeVarargs
    public final HashMap<String, Packet> unitePacketMaps(HashMap<String, Packet>... maps)
    {
        return null;
        // TODO unitePacketMaps
    }

    public HashMap<String, Packet> loadPacketsFromMemory()
    {
        Loader loader = new DefaultLoader();
        return loader.loadPacketsFromMemory();
    }

    public HashMap<String, Packet> loadPacketsFromAssets(AssetManager manager)
    {
        AssetUnpacker unpacker = new DefaultAssetUnpacker(manager);
        return unpacker.getPacketsFromAssets();
    }
    // endregion

    public Set<String> getPacketNames()
    {

        return packets.keySet();
    }

    public Packet getPacketByName(String name)
    {
        if(packets.containsKey(name))
            return packets.get(name);
        else return null; //TODO throw exception
    }
}
