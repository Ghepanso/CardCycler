import java.io.File;
import java.security.KeyException;
import java.util.HashMap;
import java.util.Set;

public class CardCycler
{
    public static void main(String args[])
    {
        var thisStuff = new CardCycler();
        Cycler cycler = null;
        try
        {
            var p = thisStuff.getPacket("Osmin");
            cycler = new Cycler(p.getCards());
        }
        catch (KeyException e)
        {
            e.printStackTrace();
        }
        if(cycler != null)
        {
            for (int i = 0; i < 15; i++)
            {
                System.out.println(cycler.currentCard);
                cycler.setNextCard();
            }
        }
    }

    static final String defaultPacketFolderName = "Packets";
    private HashMap<String, Packet> packets;

    public CardCycler()
    {
        packets = getPackets(defaultPacketFolderName);
    }

    public Packet getPacket(String key) throws KeyException
    {
        if(packets.containsKey(key))
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

    public HashMap<String, Packet> getPackets(String packetFolderName)
    {
        var result = new HashMap<String, Packet>();
        var packetsFolder = new File(packetFolderName);
        var nestedFiles = packetsFolder.listFiles();
        if(nestedFiles != null)
            for (var file : nestedFiles)
                if (file.isDirectory())
                    result.put(file.getName(), new Packet(file.getName(), file));
        return result;
    }
}
