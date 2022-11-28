package com.rahatbek.repository.implrepo;

import com.rahatbek.Util;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;
import com.rahatbek.repository.DeveloperRepository;
import com.rahatbek.repository.SkillRepository;
import com.rahatbek.repository.SpecialtyRepository;
import com.rahatbek.view.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final static String FILE_NAME = "skills.json";

    public GsonSkillRepositoryImpl() {
    }

    @Override
    public Skill getById(Long id) throws Exception {
        List<Skill> skills = stringToData(Util.read(FILE_NAME));
        Skill current = null;

        for (Skill c : skills) {
            if (c.getId().equals(id)) {
                current = c;
                break;
            }
        }
        if (current != null) {
            return current;
        }
        throw new Exception(Message.NOT_FIND_ID.getMessage() + id);
    }

    @Override
    public void delete(Long id) throws Exception {
        List<Skill> skills = stringToData(Util.read(FILE_NAME));

        for (Skill c : skills) {
            if (c.getId().equals(id)) {
                c.setStatus(Status.DELETED);
                break;
            }
        }
        Util.writeList(FILE_NAME, dataToString(skills));
    }

    @Override
    public void update(Skill item) throws Exception {
        getById(item.getId());
        save(item);
    }

    @Override
    public void save(Skill item) {
        Util.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Skill> getAll() throws Exception {
        return stringToData(Util.read(FILE_NAME));
    }

    @Override
    public Long getLastId() throws Exception {
        List<Skill> skills = stringToData(Util.read(FILE_NAME));
        Collections.sort(skills, Comparator.comparing(Skill::getId));

        if (skills.size() != 0)
            return skills.get(skills.size() - 1).getId();
        return 0L;
    }

    @Override
    public List<Skill> stringToData(List<String> items) throws Exception {
        List<Skill> skills = new ArrayList<>();

        for (String str : items) {
            String[] parts = str.split(", ");
            Skill skill = new Skill();
            skill.setId(Long.parseLong(parts[0]));
            skill.setName(parts[1]);
            skill.setStatus(Status.valueOf(parts[2]));
            skills.add(skill);
        }
        return skills;
    }

    @Override
    public List<String> dataToString(List<Skill> items) {
        List<String> data = new ArrayList<>();
        for (Skill c : items) {
            data.add(dataToString(c));
        }
        return data;
    }

    @Override
    public String dataToString(Skill item) {
        return item.getId() + ", " + item.getName() + ", " + item.getStatus();
    }

}
