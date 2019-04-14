import java.io.File;
import java.util.ArrayList;

public class Packet
{
    public String name;
    public boolean loaded = false;

    public File folder;
    public Node root;

    public Packet(String name, File folder)
    {
        this.name = name;
        this.folder = folder;
    }


    public void loadIfUnloaded()
    {
        if(!loaded)
        {
            root = FolderWalker.getNodeTree(folder);
            loaded = true;
        }
    }

    public ArrayList<Card> getCards()
    {
        loadIfUnloaded();
        return FolderWalker.getLeaves(folder);
    }

    public String toString()
    {
        return name;
    }
}
