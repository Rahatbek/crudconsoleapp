package com.rahatbek.repository.gson;

import com.google.gson.Gson;
import com.rahatbek.model.Skill;
import com.rahatbek.repository.GenericRepository;

import java.util.ArrayList;
import java.util.List;

public class GsonSkillRepositoryImpl implements GenericRepository<Skill, Long> {

    private final String SKILL_FILE_PATH = "skills.json";
    private final Gson GSON = new Gson();


    private List<Skill> getAllSkillsFromFile() {
        String skillsJsonString = "";
        //TODO: convert string to List<Skill>
        List<Skill> skills = new ArrayList<>();
        return skills;
    }

    private void writeSkillsToFile(List<Skill> skills) {
        String skillsJsonString = GSON.toJson(skills);
        //TODO: write data to the file
    }

    @Override
    public Skill getById(Long id) {
        return getAllSkillsFromFile().stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Skill update(Skill item) {
        return null;
    }

    @Override
    public Skill save(Skill skillToSave) {
        //TODO: generate ID
        //TODO: assign new id to the skillToSave
        //TODO: add skillToSave to list
        //TODO: write skill list to the file
        return null;
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkillsFromFile();
    }
}
