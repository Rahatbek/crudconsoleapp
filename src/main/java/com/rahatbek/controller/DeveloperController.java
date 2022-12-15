package com.rahatbek.controller;

import com.rahatbek.model.Developer;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;
import com.rahatbek.repository.DeveloperRepository;
import com.rahatbek.repository.SkillRepository;
import com.rahatbek.repository.SpecialtyRepository;

import java.util.List;

public class DeveloperController {

    DeveloperRepository developerRepository;

    public DeveloperController(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }

    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }

    public Developer update(long id, String firstName, String lastName, Specialty specialty, List<Skill> skills) {
        Developer updatedDeveloper = developerRepository.getById(id);
        updatedDeveloper.setFirstName(firstName);
        updatedDeveloper.setLastName(lastName);
        updatedDeveloper.setSpecialty(specialty);
        updatedDeveloper.setSkills(skills);
        updatedDeveloper.setStatus(Status.ACTIVE);

        return developerRepository.update(updatedDeveloper);
    }

    public Developer save(String firstName, String lastName, Specialty specialty, List<Skill> skills) {
        Developer newDeveloper = new Developer();
        newDeveloper.setFirstName(firstName);
        newDeveloper.setLastName(lastName);
        newDeveloper.setSpecialty(specialty);
        newDeveloper.setSkills(skills);
        newDeveloper.setStatus(Status.ACTIVE);
        return developerRepository.save(newDeveloper);
    }
}
