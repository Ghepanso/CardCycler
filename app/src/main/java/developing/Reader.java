package developing;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Reader {


    public static void main(String[] args) {
        String pName = "Osmin";
        readAll("", pName, checkSumLen);
    }


    static final int checkSumLen = 6;
    static final String packetFolderName = "Packets_old";
    static final String sep = File.separator;
    static final String configNameExt = ".pck";

    // region FolderWalker
    public static String createPacketPath(String packetName) {
        return String.join(sep, packetFolderName, packetName);

    }

    public static String createPacketPath(String... tokens) {
        return createPacketPath(String.join(sep, tokens));

    }

    public static void println(Object... tokens) {
        System.out.println(String.join(sep, Arrays.stream(tokens).map(Object::toString).collect(Collectors.toList())));

    }
    // endregion


    public static void readAll(String path, String packetName, int csLen) {
        //var packetsRoot = Paths.get(packetFolderName);

        Path curPacketPath = Paths.get(createPacketPath(packetName));
        Path configPath = Paths.get(createPacketPath(packetName, packetName + configNameExt));

        File packetRoot = curPacketPath.toFile();
        File config = configPath.toFile();
        if (packetRoot.exists() && packetRoot.isDirectory()) {
            if (config.exists()) {
                println(0);
            } else {
                developing.DefaultConfig cf = buildConfig();
            }
        } else {
            println(3);
        }


        // System.out.println(p.toAbsolutePath());

    }

    public static developing.DefaultConfig buildConfig() {
        DefaultWalker walker = new DefaultWalker<developing.DefaultConfig>();
        return null;
        //return walker.buildConfig();
    }


}
