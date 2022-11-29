package com.rahatbek.practice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rahatbek.model.Developer;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws IOException {
        String path = "companies.json";

        Gson gson = new Gson();

        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();

        Writer writer = Files.newBufferedWriter(Paths.get(path));

        Developer developer = new Developer();
        developer.setId(1L);
        developer.setFirstName("Dima");
        developer.setLastName("Ivanov");

        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("developer");
        specialty.setStatus(Status.ACTIVE);

        developer.setSpecialty(specialty);

        Skill java = new Skill();
        java.setId(1L);
        java.setName("java");
        java.setStatus(Status.ACTIVE);

        Skill oracle = new Skill();
        oracle.setId(2L);
        oracle.setName("oracle");
        oracle.setStatus(Status.ACTIVE);

        Set<Skill> skills = new HashSet<>();
        skills.add(java);
        skills.add(oracle);

        developer.setSkills(skills);

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            String jsonString = gsonBuilder.toJson(developer);
            out.write(jsonString);
        }
//        gson.toJson(123.45, new FileWriter(path));
//        gson.toJson(developer, new FileWriter(path));

//        gsonBuilder.toJson(developer, writer);

        System.out.println(developer);
    }
}
