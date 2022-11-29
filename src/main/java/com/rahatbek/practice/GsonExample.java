package com.rahatbek.practice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonExample {
    public static void main(String[] args) {

        try (JsonWriter writer = new JsonWriter(new FileWriter("user.json"))) {

            Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();

            writer.beginObject();                   // {
            writer.name("name").value("mkyong");    // "name" : "mkyong"
            writer.name("age").value(29);           // "age" : 29

            writer.name("messages");                // "messages" :
            writer.beginArray();                    // [
            writer.value("msg 1");                  // "msg 1"
            writer.value("msg 2");                  // "msg 2"
            writer.value("msg 3");                  // "msg 3"
            writer.endArray();                      // ]

            writer.endObject();                     // }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class GsonExample2 {

    public static void main(String[] args) {

        try (JsonReader reader = new JsonReader(new FileReader("user.json"))) {

            reader.beginObject();

            while (reader.hasNext()) {

                String name = reader.nextName();

                if (name.equals("name")) {

                    System.out.println(reader.nextString());

                } else if (name.equals("age")) {

                    System.out.println(reader.nextInt());

                } else if (name.equals("messages")) {

                    // read array
                    reader.beginArray();

                    while (reader.hasNext()) {
                        System.out.println(reader.nextString());
                    }

                    reader.endArray();

                } else {
                    reader.skipValue(); //avoid some unhandle events
                }
            }

            reader.endObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}