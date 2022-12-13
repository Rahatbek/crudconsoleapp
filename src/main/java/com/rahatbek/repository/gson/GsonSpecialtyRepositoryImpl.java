package com.rahatbek.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;
import com.rahatbek.repository.SpecialtyRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    private final String SPECIALTY_FILE_PATH = "./src/main/resources/specialties.json";
    private final Gson GSON = new Gson();

    private List<Specialty> getAllSpecialtiesFromFile() {
        List<Specialty> specialties = new ArrayList<>();
        try(Reader reader = new FileReader(SPECIALTY_FILE_PATH)) {
            specialties = GSON.fromJson(reader, new TypeToken<List<Specialty>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return specialties;
    }

    private void writeSpecialtiesToFile(List<Specialty> specialties) {
        String specialtiesJsonString = GSON.toJson(specialties);
        try (FileWriter writer = new FileWriter(SPECIALTY_FILE_PATH)) {
            writer.write(specialtiesJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Specialty getById(Long id) {
        return getAllSpecialtiesFromFile().stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Specialty specialtyForDelete = getById(id);
        specialtyForDelete.setStatus(Status.DELETED);
        update(specialtyForDelete);
    }

    @Override
    public Specialty update(Specialty item) {
        List<Specialty> allSpecialtiesFromFile = getAllSpecialtiesFromFile();
        List<Specialty> specialtyListForUpdate = new ArrayList<>(allSpecialtiesFromFile);
        specialtyListForUpdate.set(Math.toIntExact(item.getId() - 1), item);
        writeSpecialtiesToFile(specialtyListForUpdate);
        return item;
    }

    @Override
    public Specialty save(Specialty item) {
        Specialty specialty = getAllSpecialtiesFromFile().stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        if (specialty.getId() != null)
            item.setId(specialty.getId() + 1);
        else
            item.setId(1L);
        List<Specialty> allSpecialtiesFromFile = getAllSpecialtiesFromFile();
        allSpecialtiesFromFile.add(item);
        writeSpecialtiesToFile(allSpecialtiesFromFile);
        return item;
    }

    @Override
    public List<Specialty> getAll() {
        return getAllSpecialtiesFromFile();
    }
}
