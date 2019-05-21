package com.mathmech.cards.loading.interfaces;

import com.mathmech.cards.cycling.Packet;


public interface PacketParser {
    Packet parsePacketFromBytes(String packetName, byte[] bytes);
}