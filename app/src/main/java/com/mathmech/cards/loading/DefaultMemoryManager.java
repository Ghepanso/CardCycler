package com.mathmech.cards.loading;

import com.mathmech.cards.loading.interfaces.MemoryManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultMemoryManager implements MemoryManager {

    private final String defaultPath = "data/data/com.mathmech.cards";

    public List<String> listDirectory(String relativePathToDir) {
        File file = new File(defaultPath + File.separatorChar + relativePathToDir);
        ArrayList<String> files = new ArrayList<>();
        for (File inFile : file.listFiles()) {
            files.add(inFile.getName());
        }
        return files;
    }

    public byte[] readFile(String relativePathToFile, String fileName) {
        File folder = new File(defaultPath + File.separatorChar + relativePathToFile);
        File file = new File(folder.getAbsolutePath() + File.separatorChar + fileName);
        byte[] readBytes = new byte[(int) file.length()];
        try {
            FileInputStream stream = new FileInputStream(file);
            stream.read(readBytes, 0, (int) file.length());
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readBytes;
    }

    public void writeFile(String relativePathToFile, String fileName, byte[] toWrite) {
        File folder = new File(defaultPath + File.separatorChar + relativePathToFile);
        File file = new File(folder.getAbsoluteFile().toString() + File.separatorChar + fileName);
        if (!folder.exists())
            folder.mkdir();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(toWrite);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}