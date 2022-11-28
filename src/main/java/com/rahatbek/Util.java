package com.rahatbek;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Util {

    private final static String pathToFile = "/src/main/java/com/rahatbek/view/files/";

    public static List<String> read(String fileName) {

        List<String> data = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(GetPath(fileName)), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public static void write(String fileName, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GetPath(fileName), true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeList(String fileName, List<String> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GetPath(fileName), false))) {

        }
    }

    private static String GetPath(String fileName) {
        Path path = Paths.get("").toAbsolutePath();
        String strPath = path + pathToFile + fileName;
        return strPath;
    }
}