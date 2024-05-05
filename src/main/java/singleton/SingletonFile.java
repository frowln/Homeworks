package singleton;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class SingletonFile {
    private static SingletonFile instance;

    public static SingletonFile getInstance() {
        if (instance == null) {
            synchronized (SingletonFile.class){
                if (instance == null){
                    instance = new SingletonFile();
                }
            }
        }
        return instance;
    }

    public String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (FileReader reader = new FileReader(fileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                content.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void writeFile(String fileName, String data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object t){
        return false;
    }
}