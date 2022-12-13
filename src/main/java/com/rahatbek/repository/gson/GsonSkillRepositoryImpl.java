package com.rahatbek.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;
import com.rahatbek.repository.GenericRepository;
import com.rahatbek.repository.SkillRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GsonSkillRepositoryImpl implements GenericRepository<Skill, Long>, SkillRepository {

    private final String SKILL_FILE_PATH = "./src/main/resources/skills.json";
    private final Gson GSON = new Gson();


    private List<Skill> getAllSkillsFromFile() {
        String skillsJsonString = "";
        //TODO: convert string to List<Skill>
        List<Skill> skills = new ArrayList<>();
        try (Reader reader = new FileReader(SKILL_FILE_PATH)) {
            skills = GSON.fromJson(reader, new TypeToken<List<Skill>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return skills;
    }

    private void writeSkillsToFile(List<Skill> skills) {
        String skillsJsonString = GSON.toJson(skills);
        //TODO: write data to the file
        try (FileWriter writer = new FileWriter(SKILL_FILE_PATH)) {
            writer.write(skillsJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill getById(Long id) {
        return getAllSkillsFromFile().stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> allSkillsFromFile = getAllSkillsFromFile();
        Skill skillForDelete = getById(id);
//        allSkillsFromFile.remove(skillForDelete);
        skillForDelete.setStatus(Status.DELETED);

        update(skillForDelete);
//        writeSkillsToFile(allSkillsFromFile);
    }

    @Override
    public Skill update(Skill item) {
        List<Skill> allSkillsFromFile = getAllSkillsFromFile();
        List<Skill> listForUpdate = new ArrayList<>(allSkillsFromFile);
        listForUpdate.set(Math.toIntExact(item.getId() - 1), item);
        writeSkillsToFile(listForUpdate);
        return item;
    }

    @Override
    public Skill save(Skill skillToSave) {
        //TODO: generate ID
        //TODO: assign new id to the skillToSave
        //TODO: add skillToSave to list
        //TODO: write skill list to the file
        Skill skill = getAllSkillsFromFile().stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        if (skill.getId() != null){
            skillToSave.setId(skill.getId()+1);
        } else
            skillToSave.setId(1L);
        List<Skill> allSkillsFromFile = getAllSkillsFromFile();
        allSkillsFromFile.add(skillToSave);
        writeSkillsToFile(allSkillsFromFile);
        return skillToSave;
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkillsFromFile();
    }
}
