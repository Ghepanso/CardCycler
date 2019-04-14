import developing.Config;
import developing.Pair;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FolderWalker
{
    static final String sep = File.separator;
    static final String configNameExt = ".pck";
    static final String leafNameExt = ".txt";

    // region stuff

    public static void println(Object... tokens)
    {
        System.out.println(String.join(" ", Arrays.stream(tokens).map(Object::toString).collect(Collectors.toList())));
    }

    private static String getFileExtension(String fileName)
    {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }

    protected static Config getConfig(String fileName)
    {
        return null; // TODO: stub method

    }
    // endregion


    public static ArrayList<Card> getLeaves(File root)
    {
        var folderStack = new Stack<File>();
        var result = new ArrayList<Card>();
        folderStack.push(root);
        while(!folderStack.isEmpty())
        {
            var folder = folderStack.pop();
            var children = folder.listFiles();
            if(children != null)
            {
                for(var child : children)
                {
                    if (child.isDirectory())
                        folderStack.push(child);
                    else
                    {
                        var fileName = child.getName();
                        var ext = getFileExtension(fileName);
                        if(ext.equals(leafNameExt))
                            result.add(new Card(child));
                    }
                }
            }
        }
        return result;
    }


    public static Node getNodeTree(File packetFolder)
    {
        // TODO: rewrite! AGAIN! AGAAAIN! -J
        var rootNode = new Node(packetFolder.getName());
        var folders = new Stack<Pair<Node, File>>();
        folders.push(new Pair<>(rootNode, packetFolder));
        var curFolderCards = new HashMap<String, Card>();
        while(!folders.isEmpty())
        {
            var entry = folders.pop();
            var node = entry.firstItem;
            var folder = entry.secondItem;
            var children = folder.listFiles();
            if(children != null)
            {
                for(var child : children)
                    if (child.isDirectory())
                    {
                        var childNode = new Node(child.getName());
                        node.incident.add(childNode);
                        folders.push(new Pair<>(childNode, child));
                    }
                    else
                        parseLeaf(curFolderCards, child);
            }
            node.leaves = new ArrayList<>(curFolderCards.values());
            curFolderCards.clear();
        }
        return rootNode;
    }


    private static void parseLeaf(HashMap<String, Card> map, File leaf)
    {
        var fileName = leaf.getName();
        var ext = getFileExtension(fileName);
        switch (ext)
        {
            case leafNameExt:
                if(!map.containsKey(fileName))
                    map.put(fileName, new Card(fileName));
                break;
            case configNameExt:
                if (!map.containsKey(fileName))
                    map.put(fileName, new Card(fileName));
                else
                    map.get(fileName).config = getConfig(fileName);
                break;
            default:
                break; // TODO: throw some stuff?
        }
    }
}
