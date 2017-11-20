package JavaPROsrc.GSON2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GsonWorker {

    public static BusinessCard readFromJsonFile(String filename) {
        Gson gson = new GsonBuilder().create();
        return (BusinessCard) gson.fromJson(loadFileToString(filename), BusinessCard.class);
    }

    private static String loadFileToString(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IllegalArgumentException("No file to load!");
        }
        StringBuilder sb = new StringBuilder();
        try {
            Files.lines(Paths.get(file.getAbsolutePath()))
                    .forEach(a -> sb.append(a));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
