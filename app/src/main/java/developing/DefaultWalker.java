package developing;

import java.io.File;
import java.util.Stack;

public class DefaultWalker<Conf> implements developing.Walker {
    public DefaultWalker() {

    }

    public Conf buildConfig(File packetFolder) {

        return null;
    }

    public Conf readConfig() {
        return null;
    }

    public void walkFolders(File packetFolder) {
        developing.Walker conf = new DefaultWalker();

        File root = packetFolder;
        File[] a = root.listFiles();
        Stack<File> folders = new Stack<File>();
        folders.push(root);

        for (File file : folders) {
            if (file.isDirectory()) {
                folders.push(file);
            } else {


            }
        }


    }
//
//    public SomeConfig buildConfig(File packetFolder)
//    {
//        getNodeTree(packetFolder);
//        return null;
//    }
//
//    public DefaultConfig readConfig()
//    {
//
//
//
//        return null;
//    }
}
