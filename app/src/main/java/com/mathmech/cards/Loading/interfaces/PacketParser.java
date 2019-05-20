package com.mathmech.cards.Loading.interfaces;

import com.mathmech.cards.cycling.Packet;

import java.util.ArrayList;
import java.util.HashMap;

public interface PacketParser
{
    Packet parsePacketsFromBytes(ArrayList<Byte> bytes);
}
