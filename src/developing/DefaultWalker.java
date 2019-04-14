package developing;
import java.io.File;
import java.util.Stack;

public class DefaultWalker<Conf> implements Walker
{
    public DefaultWalker()
    {

    }

    public Conf buildConfig(File packetFolder)
    {

        return null;
    }

    public Conf readConfig()
    {
        return null;
    }

    public void walkFolders(File packetFolder)
    {
        var conf = new DefaultWalker();

        var root = packetFolder;
        var a = root.listFiles();
        var folders = new Stack<File>();
        folders.push(root);

        for (var file : folders)
        {
            if(file.isDirectory())
            {
                folders.push(file);
            }
            else
            {


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
