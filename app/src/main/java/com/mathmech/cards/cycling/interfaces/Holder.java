package com.mathmech.cards.cycling.interfaces;
import com.mathmech.cards.cycling.Packet;
import java.util.Set;

public interface Holder
{
    Set<String> getPacketNames();
    Packet getPacketByName(String name);
}
