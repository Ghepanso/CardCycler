package com.mathmech.cards;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;

import developing.Config;
import developing.Pair;

public class FolderWalker {
    static final String sep = File.separator;
    static final String configNameExt = ".pck";
    static final String leafNameExt = ".txt";

    // region stuff

    public static void println(Object... tokens) {
        System.out.println(String.join(" ", Arrays.stream(tokens).map(Object::toString).collect(Collectors.toList())));
    }

    private static String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }

    protected static Config getConfig(String fileName) {
        return null; // TODO: stub method

    }
    // endregion


    public static ArrayList<Card> getLeaves(File root) {
        Stack<File> folderStack = new Stack<File>();
        ArrayList<Card> result = new ArrayList<Card>();
        folderStack.push(root);
        while (!folderStack.isEmpty()) {
            File folder = folderStack.pop();
            File[] children = folder.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (child.isDirectory())
                        folderStack.push(child);
                    else {
                        String fileName = child.getName();
                        String ext = getFileExtension(fileName);
                        if (ext.equals(leafNameExt))
                            result.add(new Card(child));
                    }
                }
            }
        }
        return result;
    }


    public static Node getNodeTree(File packetFolder) {
        // TODO: rewrite! AGAIN! AGAAAIN! -J
        Node rootNode = new Node(packetFolder.getName());
        Stack<Pair<Node, File>> folders = new Stack<Pair<Node, File>>();
        folders.push(new Pair<>(rootNode, packetFolder));
        HashMap curFolderCards = new HashMap<String, Card>();
        while (!folders.isEmpty()) {
            Pair<Node, File> entry = folders.pop();
            Node node = entry.firstItem;
            File folder = entry.secondItem;
            File[] children = folder.listFiles();
            if (children != null) {
                for (File child : children)
                    if (child.isDirectory()) {
                        Node childNode = new Node(child.getName());
                        node.incident.add(childNode);
                        folders.push(new Pair<>(childNode, child));
                    } else
                        parseLeaf(curFolderCards, child);
            }
            node.leaves = new ArrayList<>(curFolderCards.values());
            curFolderCards.clear();
        }
        return rootNode;
    }


    private static void parseLeaf(HashMap<String, Card> map, File leaf) {
        String fileName = leaf.getName();
        String ext = getFileExtension(fileName);
        switch (ext) {
            case leafNameExt:
                if (!map.containsKey(fileName))
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
