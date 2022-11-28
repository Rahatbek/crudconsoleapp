package com.rahatbek.service.impl;

import com.rahatbek.model.Developer;
import com.rahatbek.model.Status;
import com.rahatbek.repository.DeveloperRepository;
import com.rahatbek.service.DeveloperService;

import java.util.List;
import java.util.Set;

public class DeveloperServiceImpl implements DeveloperService {

    private DeveloperRepository developerRepo;

    public DeveloperServiceImpl(DeveloperRepository developerRepo) {
        this.developerRepo = developerRepo;
    }

    @Override
    public void create(String firstName, String lastName, Long specialtyId, Set<Long> skillsId, Status status) throws Exception {
        Developer developer = new Developer();
        developer.setId(developerRepo.getLastId() + 1);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSpecialtyId(specialtyId);
        developer.setSkillsId(skillsId);
        developer.setStatus(status);
        developerRepo.save(developer);
    }

    @Override
    public void update(Long id, String firstName, String lastName, Long specialtyId, Set<Long> skillsId, Status status) throws Exception {
        Developer developer = developerRepo.getById(id);
        developer.setId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSpecialtyId(specialtyId);
        developer.setSkillsId(skillsId);
        developer.setStatus(status);
    }

    @Override
    public void checkEdit(Long id) throws Exception {
        developerRepo.checkEdit(id);
    }

    @Override
    public void checkSkill(Long id) throws Exception {
        developerRepo.checkSkill(id);
    }

    @Override
    public void checkSpecialty(Long id) throws Exception {
        developerRepo.checkSpecialty(id);
    }

    @Override
    public Developer getById(Long id) throws Exception {
        return developerRepo.getById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Developer developer = getById(id);

        developer.setStatus(Status.DELETED);
        developerRepo.update(developer);
    }

    @Override
    public List<Developer> getAll() throws Exception {
        return developerRepo.getAll();
    }
}
