package com.mathmech.cards.Loading;

import com.mathmech.cards.Loading.interfaces.MemoryManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//TODO MEMORY MANAGER
public class DefaultMemoryManager implements MemoryManager {
    public List<String> listDirectory(String relativePathToDir) {
        File file = new File(relativePathToDir);
        ArrayList<String> files = new ArrayList<>();
        for (File inFile : file.listFiles()) {
            files.add(inFile.getName());
        }
        return files;
    }

    public byte[] readFile(String relativePathToFile, String fileName) {
        File file = new File(relativePathToFile + fileName);
        byte[] readBytes = new byte[(int) file.length()];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(readBytes, 0, (int) file.length());
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LinkedList<Byte> bytes = new LinkedList<>();
        //for (int i = 0; i < readBytes.length; i++) {
        //    bytes.add(readBytes[i]);
        //}
        return readBytes;
    }

    public void writeFile(String relativePathToFile, String fileName, byte[] toWrite)
    {
        File file = new File(relativePathToFile + fileName);
        byte[] bytes = new byte[toWrite.length];
        System.arraycopy(toWrite, 0, bytes, 0, toWrite.length);
        try
        {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(bytes, 0, toWrite.length);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
