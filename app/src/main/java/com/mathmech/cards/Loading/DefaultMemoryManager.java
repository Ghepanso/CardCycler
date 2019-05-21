package com.mathmech.cards.Loading;

import com.mathmech.cards.Loading.interfaces.MemoryManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO MEMORY MANAGER
public class DefaultMemoryManager implements MemoryManager {
    public List<String> listDirectory(String relativePathToDir) {
        File file = new File("data/data/com.mathmech.cards" + File.separatorChar + relativePathToDir);
        ArrayList<String> files = new ArrayList<>();

        File[] a = file.listFiles();
        for (File inFile : file.listFiles()) {
            files.add(inFile.getName());
        }
        return files;
    }

    public byte[] readFile(String relativePathToFile, String fileName) {
        File folder = new File("data/data/com.mathmech.cards" + File.separatorChar + relativePathToFile);
        File file = new File(folder.getAbsolutePath() + File.separatorChar + fileName);
        byte[] readBytes = new byte[(int) file.length()];
        try {
            FileInputStream buf = new FileInputStream(file);
            //BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(readBytes, 0, (int) file.length());
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readBytes;
    }

    public void writeFile(String relativePathToFile, String fileName, byte[] toWrite) {
        File folder = new File("data/data/com.mathmech.cards" + File.separatorChar + relativePathToFile);
        File file = new File(folder.getAbsoluteFile().toString() + File.separatorChar + fileName);
        if (!folder.exists())
            folder.mkdir();
        boolean state = folder.exists();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(toWrite);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
