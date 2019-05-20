package com.mathmech.cards.Loading.interfaces;
import com.mathmech.cards.cycling.Packet;

import java.util.HashMap;

public interface AssetUnpacker
{
    HashMap<String, Packet> getPacketsFromAssets();

}
