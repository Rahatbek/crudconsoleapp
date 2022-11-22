package com.rahatbek.repository;

import com.rahatbek.model.Developer;
import com.rahatbek.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository{

    private final String isDeleted = "Данный аккаунт удален";

    private DeveloperRepository developerRepo;

    public GsonDeveloperRepositoryImpl() {
    }
    public GsonDeveloperRepositoryImpl(DeveloperRepository developerRepo) {
        this.developerRepo = developerRepo;
    }

    @Override
    public Developer getById(Long id) throws Exception {
        return developerRepo.getById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Developer developer = getById(id);

        if (developer.getStatus() == Status.DELETED) {
            throw new Exception(isDeleted);
        }
        developer.setStatus(Status.DELETED);

        developerRepo.update(developer);
    }

    @Override
    public void update(Developer item) throws Exception {
        delete(item.getId());
        save(item);
    }


    @Override
    public void save(Developer item) {

    }

    @Override
    public List<Developer> getAll() throws Exception {
        return null;
    }

    @Override
    public Long getLastId() throws Exception {
        return null;
    }

    @Override
    public List<Developer> stringToData(List<String> items) throws Exception {
        List<Developer> developers = new ArrayList<>();

        for (String str : items) {
            String[] strings = str.split(",");
            Developer developer = new Developer();
        }
        return null;
    }

    @Override
    public List<String> dataToString(List<Developer> items) {
        return null;
    }

    @Override
    public String dataToString(Developer item) {
        return null;
    }

    @Override
    public void create(String firstName, String lastName, Set<Long> skillsId, Long specialtyId, Status status) throws Exception {
        Developer developer = new Developer();
        developer.setId(developerRepo.getLastId() + 1);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSkillsId(skillsId);
        developer.setSpecialtyId(specialtyId);
        developer.setStatus(status);
        developerRepo.save(developer);
    }

    @Override
    public void update(Long id, String firstName, String lastName, Set<Long> skillsId, Long specialtyId, Status status) throws Exception {

    }

    @Override
    public void create(Long id, String firstName, String lastName) {

    }

    @Override
    public void update(Long id, String firstName, String lastName) {

    }
}
