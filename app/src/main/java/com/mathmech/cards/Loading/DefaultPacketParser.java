package com.mathmech.cards.Loading;

import com.mathmech.cards.Loading.interfaces.PacketParser;
import com.mathmech.cards.cycling.Card;
import com.mathmech.cards.cycling.Packet;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DefaultPacketParser implements PacketParser
{
    final int decoder_version = 1; //TODO throw ex if reads another

    final int CARDS_COUNT_BYTES = 3;
    final int CARD_FRAME_LEN_BYTES = 5;
    final int VERSION_BYTES = 2;
    final int NAME_LEN_BYTES = 2;
    final int QUEST_LEN_BYTES = 2;
    final int TIPS_COUNT_BYTES = 2;
    final int TIP_LEN_COUNT = 3;

    private ByteArrayInputStream stream;

    // region Read methods
    private int readInt(int bytesToRead)
    {
        int result = 0;
        for (int i = 0; i < bytesToRead; i++) {
            int a = stream.read();
            result += a << (8 * (bytesToRead - i - 1));
        }
        return result;
    }

    private String readString(int length)
    {
        // Actually extremely bad crutch --Jarl
        byte[] buffer = new byte[length];
        for (int i = 0; i < length; i++)
        {
            buffer[i] = (byte)stream.read();
        }
        return new String(buffer, StandardCharsets.UTF_8);
    }

    private Card readCard()
    {
        int nameLen = readInt(NAME_LEN_BYTES);
        int questionLen = readInt(QUEST_LEN_BYTES);
        int tipsCount = readInt(TIPS_COUNT_BYTES);
        int[] tipLengths = new int[tipsCount];
        for (int i = 0; i < tipsCount; i++)
            tipLengths[i] = readInt(TIP_LEN_COUNT);

        String name = readString(nameLen);
        String question = readString(questionLen);
        String[] tips = new String[tipsCount];
        for (int i = 0; i < tipsCount; i++)
            tips[i] = readString(tipLengths[i]);

        return new Card(name, question, tips);
    }
    // endregion

    private ArrayList<Card> compile()
    {
        int version = readInt(VERSION_BYTES);
        int cardCount = readInt(CARDS_COUNT_BYTES);
        //int[] framesLengths = new int[cardCount];
        //for (int i = 0; i < cardCount; i++)
        //    framesLengths[i] = readInt(CARD_FRAME_LEN_BYTES);
        long ignoredFrameLengthInfo = stream.skip(cardCount * CARD_FRAME_LEN_BYTES);

        ArrayList<Card> result = new ArrayList<>();
        for (int i = 0; i < cardCount; i++)
            result.add(readCard());
        return result;
    }

    public Packet parsePacketFromBytes(String packetName, byte[] bytes)
    {
        stream = new ByteArrayInputStream(bytes);
        return new Packet(packetName, compile());
    }
}
