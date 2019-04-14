package com.mathmech.cards;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import developing.Config;

public class Card {
    public String name;
    public String question;
    public Config config;
    public String[] tips;

    public Card(String name) {
        // TODO: get rid of it
        this.name = name;
    }

    public Card(File content) {
        if (content != null) {
            try {
                name = content.getName();
                List<String> lines = Files.readAllLines(content.toPath());
                question = lines.get(0);
                int tipsCount = lines.size() - 1;
                tips = new String[tipsCount];
                for (int i = 0; i < tipsCount; i++) {
                    tips[i] = lines.get(i + 1);
                }
            } catch (IOException e) {   // TODO work out exception
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        return name;
    }
}
