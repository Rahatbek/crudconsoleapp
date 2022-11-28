package com.rahatbek.controller;

import com.rahatbek.model.Developer;
import com.rahatbek.model.Status;
import com.rahatbek.repository.DeveloperRepository;
import com.rahatbek.service.DeveloperService;

import java.util.List;
import java.util.Set;

public class DeveloperController {

    DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    public List<Developer> getAll() throws Exception {
        return developerService.getAll();
    }

    public Developer getById(Long id) throws Exception {
        return developerService.getById(id);
    }

    public void create(String firstName, String lastName, Long specialtyId, Set<Long> skillsId, Status status) throws Exception {
        developerService.create(firstName, lastName, specialtyId, skillsId, status);
    }

    public void update(Long id, String firstName, String lastName, Long specialtyId, Set<Long> skillsId, Status status) throws Exception {
        developerService.update(id, firstName, lastName, specialtyId, skillsId, status);
    }

    public void checkEdit(Long id) throws Exception {
        developerService.checkEdit(id);
    }

    public void checkSkill(Long id) throws Exception {
        developerService.checkSkill(id);
    }

    public void checkSpecialty(Long id) throws Exception {
        developerService.checkSpecialty(id);
    }

    public void delete(Long id) throws Exception {
        developerService.delete(id);
    }
}
