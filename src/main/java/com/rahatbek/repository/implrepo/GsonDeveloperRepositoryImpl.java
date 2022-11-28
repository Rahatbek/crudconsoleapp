package com.rahatbek.repository.implrepo;

import com.rahatbek.Util;
import com.rahatbek.model.Developer;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;
import com.rahatbek.repository.DeveloperRepository;
import com.rahatbek.repository.SkillRepository;
import com.rahatbek.repository.SpecialtyRepository;
import com.rahatbek.view.Message;

import java.util.*;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    private SpecialtyRepository specialtyRepository;
    private SkillRepository skillRepository;

    private final static String FILE_NAME = "developers.json";

    private final String cannotEditDeletedDeveloper = "Нельзя редактировать удаленного разработчика!";

    public GsonDeveloperRepositoryImpl(SpecialtyRepository specialtyRepository, SkillRepository skillRepository) {
        this.specialtyRepository = specialtyRepository;
        this.skillRepository = skillRepository;
    }

    public Developer getById(Long id) throws Exception {
        List<Developer> developers = stringToData(Util.read(FILE_NAME));

        Developer current = null;

        for (Developer developer : developers) {
            if (developer.getId().equals(id)) {
                current = developer;
                break;
            }
        }

        if (current != null) {
            return current;
        }

        throw new Exception(Message.NOT_FIND_ID.getMessage() + id);
    }

    public void delete(Long id) throws Exception {
        List<Developer> developers = stringToData(Util.read(FILE_NAME));
        Developer rmDeveloper = null;

        for (Developer d : developers) {
            if (d.getId().equals(id)) {
                d.setStatus(Status.DELETED);
                break;
            }
        }

        Util.writeList(FILE_NAME, dataToString(developers));
    }

    public void update(Developer developer) throws Exception {
        getById(developer.getId());
        save(developer);
    }

    public void save(Developer developer) {
        Util.write(FILE_NAME, dataToString(developer));
    }

    public void checkEdit(Long id) throws Exception {
        Developer developer = getById(id);

        if (developer.getStatus() == Status.DELETED) {
            throw new Exception(cannotEditDeletedDeveloper);
        }
    }

    public void checkSkill(Long id) throws Exception {
        try {
            skillRepository.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void checkSpecialty(Long id) throws Exception {
        try {
            specialtyRepository.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Developer> getAll() throws Exception {
        return stringToData(Util.read(FILE_NAME));
    }

    public Long getLastId() throws Exception {
        List<Developer> developers = stringToData(Util.read(FILE_NAME));
        Collections.sort(developers, Comparator.comparing(Developer::getId));
        if (developers.size() != 0) {
            return developers.get(developers.size() - 1).getId();
        }
        return 0L;
    }

    public List<Developer> stringToData(List<String> items) throws Exception {
        List<Developer> developers = new ArrayList<>();

        for (String str : items) {
            String[] parts = str.split(", ");
            Developer developer = new Developer();

            developer.setId(Long.parseLong(parts[0]));
            developer.setFirstName(parts[1]);
            developer.setLastName(parts[2]);

            Long specialtyId = Long.parseLong(parts[3]);
            developer.setSpecialtyId(specialtyId);

            String[] skillsId = parts[4].split("/");
            HashSet<Skill> skills = new HashSet<>();
            HashSet<Long> skillsIdHS = new HashSet<>();
            for (String id : skillsId) {
                Long skillId = Long.parseLong(id);
                skillsIdHS.add(skillId);
                skills.add(skillRepository.getById(skillId));
            }
            developer.setStatus(Status.valueOf(parts[5]));

            developer.setSkillsId(skillsIdHS);

            developers.add(developer);
        }

        return developers;
    }

    public List<String> dataToString(List<Developer> items) {
        List<String> data = new ArrayList<>();
        for (Developer dev : items) {
            data.add(dataToString(dev));
        }

        return data;
    }

    public String dataToString(Developer developer) {
        String data = developer.getId() + ", " + developer.getFirstName() + ", " + developer.getLastName() + ", "
                + developer.getSpecialtyId() + ", " + developer.getSkillsId() + ", " + developer.getStatus();
        StringJoiner joiner = new StringJoiner("/");
        for (Long c : developer.getSkillsId()) {
            joiner.add(c+"");
        }
        data += joiner;

        return data;
    }

    public boolean isContainSkill(Skill skill) throws Exception {
        List<Developer> developers = stringToData(Util.read(FILE_NAME));
        for (Developer dev : developers) {
            if (dev.getSkillsId().contains(skill)) ;
            return true;
        }
        return false;
    }

    public boolean isContainSpecialty(Specialty specialty) throws Exception {
        List<Developer> developers = stringToData(Util.read(FILE_NAME));
        for (Developer dev : developers) {
            if (dev.getSpecialtyId() == specialty.getId())
                return true;
        }
        return false;
    }
}
