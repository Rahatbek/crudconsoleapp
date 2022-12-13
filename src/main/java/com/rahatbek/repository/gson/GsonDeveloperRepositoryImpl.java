package com.rahatbek.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rahatbek.model.Developer;
import com.rahatbek.model.Status;
import com.rahatbek.repository.DeveloperRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    public static void main(String[] args) {
        GsonDeveloperRepositoryImpl repository = new GsonDeveloperRepositoryImpl();

//        System.out.println(repository.getAllDevelopersFromFle());
    }

    private final String DEVELOPER_FILE_PATH = "./src/main/resources/developers.json";
    private final Gson GSON = new Gson();

    private List<Developer> getAllDevelopersFromFle() {
        List<Developer> developers = new ArrayList<>();
        try (Reader reader = new FileReader(DEVELOPER_FILE_PATH)) {
            developers = GSON.fromJson(reader, new TypeToken<List<Developer>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developers;
    }

    private void writeDevelopersToFile(List<Developer> developers) {
        String developersJsonString = GSON.toJson(developers);
        try (FileWriter writer = new FileWriter(DEVELOPER_FILE_PATH)) {
            writer.write(developersJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(Long id) {
        return getAllDevelopersFromFle().stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Developer developerForDelete = getById(id);
        developerForDelete.setStatus(Status.DELETED);
        update(developerForDelete);
    }

    @Override
    public Developer update(Developer item) {
        List<Developer> allDevelopersFromFle = getAllDevelopersFromFle();
        List<Developer> listForUpdate = new ArrayList<>(allDevelopersFromFle);
        listForUpdate.set(Math.toIntExact(item.getId() - 1), item);
        writeDevelopersToFile(listForUpdate);
        return item;
    }

    @Override
    public Developer save(Developer item) {
        Developer developer = getAllDevelopersFromFle().stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        if (developer.getId() != null)
            item.setId(developer.getId() + 1);
        else
            item.setId(1L);
        List<Developer> allDevelopersFromFle = getAllDevelopersFromFle();
        allDevelopersFromFle.add(item);
        writeDevelopersToFile(allDevelopersFromFle);
        return item;
    }

    @Override
    public List<Developer> getAll() {
        return getAllDevelopersFromFle();
    }
}
