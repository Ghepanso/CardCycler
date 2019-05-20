package com.mathmech.cards.Loading;

import com.mathmech.cards.Loading.interfaces.Loader;
import com.mathmech.cards.Loading.interfaces.MemoryManager;
import com.mathmech.cards.Loading.interfaces.PacketParser;
import com.mathmech.cards.cycling.Packet;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultLoader implements Loader
{
    //TODO LOADER
    final String defaultPacketDirectory = "Packets";
    public HashMap<String, Packet> loadPacketsFromMemory()
    {
        PacketParser parser = new DefaultPacketParser();
        MemoryManager manager = new DefaultMemoryManager();

        HashMap<String, Packet> result = new HashMap<>();
        for (String fileName : manager.listDirectory(defaultPacketDirectory))
        {
            ArrayList<Byte> bytes = manager.readFile(defaultPacketDirectory, fileName);
            Packet packet = parser.parsePacketsFromBytes(bytes);
            result.put(packet.name, packet);
        }
        return result;
    }


}
