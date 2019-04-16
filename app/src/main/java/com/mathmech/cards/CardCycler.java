package com.mathmech.cards;

import android.content.res.AssetManager;

import java.security.KeyException;
import java.util.HashMap;
import java.util.Set;

public class CardCycler {

    static final String defaultPacketFolderName = "Packets";
    private final HashMap<String, Packet> packets;
    public final AssetUnpacker unpacker;

    public CardCycler(AssetManager manager) {
        unpacker = new AssetUnpacker(manager);
        packets = unpacker.extractPackets(defaultPacketFolderName);
    }

    public Packet getPacket(String key) throws KeyException {
        if (packets.containsKey(key)) {
            return packets.get(key);
        } else {
            throw new KeyException();
        }
    }

    public Set<String> getKeys() {
        return packets.keySet();
    }
}
