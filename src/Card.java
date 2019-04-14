import developing.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Card
{
    public String name;
    public String question;
    public Config config;
    public String[] tips;
    public Card(String name)
    {
        // TODO: get rid of it
        this.name = name;
    }

    public Card(File content)
    {
        if(content != null)
        {
            try
            {
                name = content.getName();
                var lines = Files.readAllLines(content.toPath());
                question = lines.get(0);
                var tipsCount = lines.size()-1;
                tips = new String[tipsCount];
                for (int i = 0; i < tipsCount; i++)
                {
                    tips[i] = lines.get(i+1);
                }
            }
            catch (IOException e)
            {   // TODO work out exception
                e.printStackTrace();
            }
        }
    }

    public String toString()
    {
        return name;
    }
}
