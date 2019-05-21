package com.mathmech.cards.cycling;

import android.content.res.AssetManager;
import android.content.res.Resources;

import com.mathmech.cards.cycling.interfaces.Holder;
import com.mathmech.cards.loading.DefaultAssetUnpacker;
import com.mathmech.cards.loading.DefaultLoader;
import com.mathmech.cards.loading.interfaces.AssetUnpacker;
import com.mathmech.cards.loading.interfaces.Loader;

import java.util.HashMap;
import java.util.Set;

public class DefaultHolder implements Holder {
    private final HashMap<String, Packet> packets;

    public DefaultHolder(AssetManager manager) {
        packets = getPackets(manager);
    }

    private HashMap<String, Packet> getPackets(AssetManager manager) {
        exportAssetsIfNotExported(manager);
        return loadPacketsFromMemory();
    }

    private void exportAssetsIfNotExported(AssetManager manager) {
        AssetUnpacker unpacker = new DefaultAssetUnpacker(manager);
        if (!unpacker.arePacketsExported())
            unpacker.exportPacketsToMemory();
    }

    private HashMap<String, Packet> loadPacketsFromMemory() {
        Loader loader = new DefaultLoader();
        return loader.loadPacketsFromMemory();
    }

    public Set<String> getPacketNames() {

        return packets.keySet();
    }

    public Packet getPacketByName(String name) {
        name += ".pkt";
        if (packets.containsKey(name))
            return packets.get(name);
        else
            throw new Resources.NotFoundException("No packet named like this");
    }
}