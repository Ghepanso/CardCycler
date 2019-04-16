package com.mathmech.cards;

import android.content.res.AssetManager;

import java.io.File;
import java.security.KeyException;
import java.util.HashMap;
import java.util.Set;

public class CardCycler
{
    //    public static void main(String args[]) {
    //        CardCycler thisStuff = new CardCycler();
    //        Cycler cycler = null;
    //        try {
    //            Packet p = thisStuff.getPacket("Osmin");
    //            cycler = new Cycler(p.getCards());
    //        } catch (KeyException e) {
    //            e.printStackTrace();
    //        }
    //        if (cycler != null) {
    //            for (int i = 0; i < 15; i++) {
    //                System.out.println(cycler.currentCard);
    //                cycler.setNextCard();
    //            }
    //        }
    //    }

    static final String defaultPacketFolderName = "Packets";
    private HashMap<String, Packet> packets;
    public AssetUnpacker unpacker;

    public CardCycler(AssetManager manager)
    {
        unpacker = new AssetUnpacker(manager);
        packets = unpacker.extractPackets(defaultPacketFolderName);
    }

    public Packet getPacket(String key) throws KeyException
    {
        if (packets.containsKey(key))
        {
            return packets.get(key);
        }
        else
        {
            throw new KeyException();
        }
    }

    public Set<String> getKeys()
    {
        return packets.keySet();

    }

}
