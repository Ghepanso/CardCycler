package developing;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Reader
{


    public static void main(String[] args)
    {
        var pName = "Osmin";
        readAll("", pName, checkSumLen);
    }


    static final int checkSumLen = 6;
    static final String packetFolderName = "Packets";
    static final String sep = File.separator;
    static final String configNameExt = ".pck";

    // region FolderWalker
    public static String createPacketPath(String packetName)
    {
        return String.join(sep, packetFolderName, packetName);

    }
    public static String createPacketPath(String ... tokens)
    {
        return createPacketPath(String.join(sep, tokens));

    }

    public static void println(Object... tokens)
    {
        System.out.println(String.join(sep, Arrays.stream(tokens).map(Object::toString).collect(Collectors.toList())));

    }
    // endregion



    public static void readAll(String path, String packetName, int csLen)
    {
        //var packetsRoot = Paths.get(packetFolderName);

        var curPacketPath = Paths.get(createPacketPath(packetName));
        var configPath = Paths.get(createPacketPath(packetName, packetName + configNameExt));

        var packetRoot = curPacketPath.toFile();
        var config = configPath.toFile();
        if(packetRoot.exists() && packetRoot.isDirectory())
        {
            if(config.exists())
            {
                println(0);
            }
            else
            {
                var cf = buildConfig();
            }
        }
        else
        {
            println(3);
        }







        // System.out.println(p.toAbsolutePath());

    }

    public static DefaultConfig buildConfig()
    {
        var walker = new DefaultWalker<DefaultConfig>();
        return null;
        //return walker.buildConfig();
    }


}
