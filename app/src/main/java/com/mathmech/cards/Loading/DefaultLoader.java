package com.mathmech.cards.Loading;

import com.mathmech.cards.Loading.interfaces.Loader;
import com.mathmech.cards.Loading.interfaces.MemoryManager;
import com.mathmech.cards.cycling.Packet;

import java.util.HashMap;

public class DefaultLoader implements Loader {
    private final String defaultPacketDirectory = "Packets";

    public HashMap<String, Packet> loadPacketsFromMemory() {
        MemoryManager manager = new DefaultMemoryManager();

        HashMap<String, Packet> result = new HashMap<>();
        for (String fileName : manager.listDirectory(defaultPacketDirectory)) {
            byte[] bytes = manager.readFile(defaultPacketDirectory, fileName);
            Packet packet = new DefaultPacketParser().parsePacketFromBytes(fileName, bytes);
            result.put(packet.name, packet);
        }
        return result;
    }
}