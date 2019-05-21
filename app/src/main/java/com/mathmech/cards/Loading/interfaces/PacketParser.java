package com.mathmech.cards.Loading.interfaces;

import com.mathmech.cards.cycling.Packet;


public interface PacketParser {
    Packet parsePacketFromBytes(String packetName, byte[] bytes);
}